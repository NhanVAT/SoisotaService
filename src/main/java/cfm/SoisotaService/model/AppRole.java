package cfm.SoisotaService.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "app_role")
public class AppRole {

    @Id
    @Size(min = 1, max = 50, message = "Max name role length: 50 characters")
    @Column(unique = true, nullable = false)
    private String name;
}
