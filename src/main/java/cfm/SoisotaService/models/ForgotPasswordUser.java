package cfm.SoisotaService.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ForgotPasswordUser {

  @NotNull(message = "Chưa truyền Email or SĐT")
  @NotEmpty
  private String emailOrPhone;
  private String userName;
  private String email;
  private String phone;
  private String typeSendPassword;
}
