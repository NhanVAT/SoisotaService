package cfm.SoisotaService.dto;

import cfm.SoisotaService.entities.AppRole;
import java.time.Instant;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDataDTO {

  List<AppRole> appRoles;
  private String userName;
  private String userId;
  private String email;
  private String password;
  private Boolean active;
  private String createdBy;
  private Instant createdDate;
  private String lastModifiedBy;
  private Instant lastModifiedDate;
  private String fullName;
  private String address;
  private String phone;
  private Long id;
  private String confirmPassword;
  private byte[] avatarImage;
  private Integer sexUser;
  private List<Long> lstRoleId;
}
