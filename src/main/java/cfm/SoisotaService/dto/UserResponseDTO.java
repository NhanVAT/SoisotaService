package cfm.SoisotaService.dto;

import cfm.SoisotaService.entities.AppRole;
import java.time.Instant;
import java.util.List;
import lombok.Data;

@Data
public class UserResponseDTO {

  List<AppRole> appRoles;
  private Integer id;
  private String userName;
  private String userId;
  private String email;
  private String phone;
  private String fullName;
  private String address;
  private Integer prestigePoints;
  private byte[] avatarImage;
  private Integer sexUser;
  private String password;
  private Boolean active;
  private String createdBy;
  private Instant createdDate;
  private String lastModifiedBy;
  private Instant lastModifiedDate;

}
