package cfm.SoisotaService.dto;

import cfm.SoisotaService.entities.AppRole;
import io.swagger.annotations.ApiModelProperty;
import java.time.Instant;
import java.util.List;
import lombok.Data;

@Data
public class UserResponseDTO {

  @ApiModelProperty(position = 9)
  List<AppRole> appRoles;
  @ApiModelProperty(position = 0)
  private Integer id;
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

}
