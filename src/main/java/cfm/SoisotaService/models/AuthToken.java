package cfm.SoisotaService.models;

import lombok.Data;

@Data
public class AuthToken {

  private String token;
  private Integer sessionId;

  public AuthToken() {
  }

  public AuthToken(String token) {
    this.token = token;
    this.sessionId = (int) Math.floor(Math.random() * (1000000000 - 10000000 + 1) + 10000000);
  }
}
