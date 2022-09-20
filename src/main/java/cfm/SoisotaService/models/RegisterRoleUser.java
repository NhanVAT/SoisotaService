package cfm.SoisotaService.models;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
public class RegisterRoleUser {
    @NotBlank(message = "First Name không được để trống")
    private String firstName;

    @NotBlank(message = "Last Name không được để trống")

    private String lastName;

    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Password không được để trống")
    @Min(value = 8, message = "Password phải từ 8 kí tự trở lên")
    private String password;

    @NotBlank(message = "Confirm Password không được để trống")
    private String confirmPassword;

    @NotBlank(message = "Bạn cần đọc và chấp nhận quy định mới được đăng ký")
    private String acceptTerm;
}
