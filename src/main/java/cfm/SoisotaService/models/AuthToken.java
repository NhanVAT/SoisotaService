package cfm.SoisotaService.models;

import lombok.Data;

@Data
public class AuthToken {

  private String accessToken;
  private String tokenType;
  private String refreshToken;
  private Integer sessionId;

  public AuthToken() {
  }

  public AuthToken(String accessToken, String tokenType, String refreshToken) {
    this.accessToken = accessToken;
    this.tokenType = tokenType;
    this.refreshToken = refreshToken;
    this.sessionId = (int) Math.floor(Math.random() * (1000000000 - 10000000 + 1) + 10000000);
  }
}
