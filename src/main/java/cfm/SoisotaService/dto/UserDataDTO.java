package cfm.SoisotaService.dto;

import cfm.SoisotaService.entities.AppRole;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDataDTO {

  @ApiModelProperty(position = 9)
  List<AppRole> appRoles;
  @ApiModelProperty(position = 1)
  private String userName;
  @ApiModelProperty(position = 10)
  private String userId;
  @ApiModelProperty(position = 2)
  private String email;
  @ApiModelProperty(position = 3)
  private String password;
  @ApiModelProperty(position = 4)
  private Boolean active;
  @ApiModelProperty(position = 5)
  private String createdBy;
  @ApiModelProperty(position = 6)
  private Instant createdDate;
  @ApiModelProperty(position = 7)
  private String lastModifiedBy;
  @ApiModelProperty(position = 8)
  private Instant lastModifiedDate;
  @ApiModelProperty(position = 11)
  private String fullName;
  @ApiModelProperty(position = 12)
  private String address;
  @ApiModelProperty(position = 13)
  private String phone;
  @ApiModelProperty(position = 14)
  private Long id;
  @ApiModelProperty(position = 15)
  private String confirmPassword;

}
