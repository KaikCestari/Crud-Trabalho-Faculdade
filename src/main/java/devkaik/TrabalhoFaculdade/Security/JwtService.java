package devkaik.TrabalhoFaculdade.Security;

import devkaik.TrabalhoFaculdade.Model.Entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtService {

    private final Key chaveAssinatura;
    private final long expiracao;

    public JwtService(
            @Value("${app.security.jwt.secret}") String segredo,
            @Value("${app.security.jwt.expiration}") long expiracao
    ) {
        this.chaveAssinatura = Keys.hmacShaKeyFor(Decoders.BASE64.decode(segredo));
        this.expiracao = expiracao;
    }

    public String gerarToken(Usuario usuario) {
        Date agora = new Date();
        Date expira = new Date(agora.getTime() + expiracao);

        return Jwts.builder()
                .setSubject(usuario.getUsername())
                .setIssuedAt(agora)
                .setExpiration(expira)
                .claim("role", usuario.getUsuarioEnum().name())
                .signWith(chaveAssinatura, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extrairUsername(String token) {
        return extrairClaim(token, Claims::getSubject);
    }

    public boolean tokenValido(String token, Usuario usuario) {
        String username = extrairUsername(token);
        return username.equals(usuario.getUsername()) && !tokenExpirado(token);
    }

    private boolean tokenExpirado(String token) {
        Date expiration = extrairClaim(token, Claims::getExpiration);
        return expiration.before(new Date());
    }

    private <T> T extrairClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(chaveAssinatura)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
}
