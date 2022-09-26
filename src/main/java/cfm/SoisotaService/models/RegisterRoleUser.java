package cfm.SoisotaService.models;

import lombok.Data;

import javax.validation.constraints.*;


@Data
public class RegisterRoleUser {
    public RegisterRoleUser(String firstName, String lastName, String email, String password, String confirmPassword, boolean acceptTerm, String address, String phone, String userName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.acceptTerm = acceptTerm;
        this.address = address;
        this.phone = phone;
        this.userName = userName;
        this.fullName = firstName + " " + lastName;
        this.userId = userName;
    }

    //@NotEmpty(message = "First Name không được để trống")
    private String firstName;

//    @NotEmpty(message = "Last Name không được để trống")

    private String lastName;

//    @Email(message = "Email không hợp lệ")
    private String email;

//    @NotEmpty(message = "Password không được để trống")
//    @Min(value = 8, message = "Password phải từ 8 kí tự trở lên")
    private String password;

//    @NotEmpty(message = "Confirm Password không được để trống")
    private String confirmPassword;

//    @NotNull
//    @AssertTrue
    private boolean acceptTerm;

//    @NotEmpty
    private String address;

    //    @NotEmpty
    private String phone;

    private String fullName;

    //    @NotEmpty
    private String userName;

    private String userId;
}
