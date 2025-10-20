package devkaik.TrabalhoFaculdade.controller.dto;

public record TokenResponse(String accessToken, String tokenType, long expiresIn) {
}
