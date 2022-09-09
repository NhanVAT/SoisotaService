package cfm.SoisotaService.dto;

import cfm.SoisotaService.entities.AppRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDataDTO {

    @ApiModelProperty(position = 1)
    private String username;
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
    @ApiModelProperty(position = 9)
    List<AppRole> appRoles;

}
