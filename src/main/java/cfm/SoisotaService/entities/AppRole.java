package cfm.SoisotaService.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "app_roles")
public class AppRole extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Size(min = 1, max = 20, message = "Max id role length: 20 characters")
  @Column(name = "role_id", nullable = false)
  private String roleId;

  @Size(min = 1, max = 20, message = "Max id role length: 20 characters")
  @Column(name = "role_key", nullable = false)
  private String roleKey;

  @Size(min = 1, max = 50, message = "Max name role length: 50 characters")
  @Column(name = "role_name", nullable = false)
  private String roleName;

  @Column(name = "role_describe")
  private String roleDescribe;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
  @JoinTable(name = "app_role_menus",
      joinColumns = {
          @JoinColumn(name = "role_id")
      },
      inverseJoinColumns = {
          @JoinColumn(name = "menu_id")})
  private Set<AppMenu> menus;

}
