package cfm.SoisotaService.dto;

import cfm.SoisotaService.entities.AppMenu;
import java.time.Instant;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RoleDataDTO {

  private Long id;
  private String roleId;
  private String roleKey;
  private String roleName;
  private String roleDescribe;
  private List<MenuDataDTO> menus;
  private List<Long> lstMenuId;
  private Boolean active;
  private String createdBy;
  private Instant createdDate;
  private String lastModifiedBy;
  private Instant lastModifiedDate;
}
