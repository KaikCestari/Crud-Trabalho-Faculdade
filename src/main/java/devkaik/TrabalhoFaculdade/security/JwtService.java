package devkaik.TrabalhoFaculdade.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private final String secret;
    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private final Duration expiration = Duration.ofHours(1);

    public JwtService(@Value("${app.security.jwt.secret}") String secret) {
        this.secret = secret;
    }

    @PostConstruct
    void init() {
        SecretKey secretKey = getSecretKey();
        this.jwtEncoder = new NimbusJwtEncoder(new ImmutableSecret<>(secretKey));
        this.jwtDecoder = NimbusJwtDecoder.withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }

    public String generateToken(UserDetails userDetails) {
        Instant now = Instant.now();
        String scope = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(userDetails.getUsername())
                .issuedAt(now)
                .expiresAt(now.plus(expiration))
                .claim("scope", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            Jwt jwt = decode(token);
            Instant expiresAt = jwt.getExpiresAt();
            return userDetails.getUsername().equals(jwt.getSubject())
                    && expiresAt != null
                    && expiresAt.isAfter(Instant.now());
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }

    public String extractEmail(String token) {
        try {
            return decode(token).getSubject();
        } catch (JwtException | IllegalArgumentException ex) {
            return null;
        }
    }

    public long getExpirationInSeconds() {
        return expiration.toSeconds();
    }

    private Jwt decode(String token) {
        return jwtDecoder.decode(token);
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }
}
