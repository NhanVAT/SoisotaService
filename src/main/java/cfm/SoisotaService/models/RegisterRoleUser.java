package cfm.SoisotaService.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class RegisterRoleUser {

  @NotEmpty
  private String userName;
  @Email(message = "Email không hợp lệ")
  private String email;
  @NotEmpty
  private String phone;
  @NotEmpty(message = "Password không được để trống")
  private String password;
  private String confirmPassword;
  private boolean acceptTerm;
  private String address;
  @NotEmpty
  private String fullName;
  private String userId;
  private Integer sexUser;
  private byte[] avatarImage;
}
