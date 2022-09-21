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
@Table(name = "app_users")
public class AppUser extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Size(min = 1, max = 20, message = "Max id role length: 20 characters")
  @Column(name = "user_id", nullable = false)
  private String userId;

  @Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
  @Column(name = "user_name", unique = true, nullable = false)
  private String userName;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "fullName")
  private String fullName;

  @Column(name = "address")
  private String address;

  @Size(min = 8, message = "Minimum password length: 8 characters")
  @Column(name = "password")
  private String password;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
  @JoinTable(name = "app_user_roles", joinColumns = {
      @JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
  private Set<AppRole> roles;

}
