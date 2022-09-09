package cfm.SoisotaService.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

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

    @Size(min = 1, max = 50, message = "Max name role length: 50 characters")
    @Column(name = "role_name", nullable = false)
    private String roleName;
}
