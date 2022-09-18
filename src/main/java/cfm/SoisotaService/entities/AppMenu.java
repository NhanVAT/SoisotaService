package cfm.SoisotaService.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "app_menu")
public class AppMenu extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  //Menu ở cấp cha sẽ là -1 còn lại thì nó sẽ bằng ID của menu luôn
  @Size(min = 1, max = 20, message = "Max id role length: 20 characters")
  @Column(name = "menu_id", nullable = false)
  private String menuId;

  @Column(name = "menu_parent")
  private Long menuParent;

  @Column(name = "menu_order", nullable = false)
  private Long menuOrder;

  @Column(name = "menu_name", nullable = false)
  private String menuName;

  @Column(name = "menu_prj_name")
  private String menuPrjName;

  @Column(name = "menu_icon")
  private String menuIcon;

  @Column(name = "menu_target")
  private String menuTarget;

  @Column(name = "menu_url")
  private String menuUrl;

  @Column(name = "menu_router_link", nullable = false)
  private String menuRouterLink;

  @Column(name = "menu_badge")
  private String menuBadge;

  @Column(name = "menu_badge_class")
  private String menuBadgeClass;

}

