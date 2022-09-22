package cfm.SoisotaService.dto;

import cfm.SoisotaService.entities.AppMenu;
import java.time.Instant;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuDataDTO {

  private Long id;
  private Long key;
  private String menuId;
  private Long menuParent;
  private Long menuOrder;
  private String menuName;
  private String menuPrjName;
  private String menuIcon;
  private String menuTarget;
  private String menuUrl;
  private String menuRouterLink;
  private String menuBadge;
  private String menuBadgeClass;
  private Boolean active;
  private String createdBy;
  private Instant createdDate;
  private String lastModifiedBy;
  private Instant lastModifiedDate;
}
