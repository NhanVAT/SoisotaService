package cfm.SoisotaService.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@Table(name = "app_roles")
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(min = 1, max = 20, message = "Max id role length: 20 characters")
    @Column(name = "role_id", nullable = false)
    private String roleId;

    @Size(min = 1, max = 50, message = "Max name role length: 50 characters")
    @Column(name = "role_name", nullable = false)
    private String roleName;
}
