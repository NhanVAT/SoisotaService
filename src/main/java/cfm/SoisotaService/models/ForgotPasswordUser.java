package cfm.SoisotaService.models;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ForgotPasswordUser {

  private String userName;
  private String email;
  private String phone;
  private String typeSendPassword;
}
