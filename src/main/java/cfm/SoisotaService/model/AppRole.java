package cfm.SoisotaService.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "app_role")
public class AppRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 1, max = 50, message = "Max name role length: 50 characters")
    @Column(unique = true, nullable = false)
    private String name;

    @Size(min = 1, max = 200, message = "Max describe role length: 50 characters")
    @Column(unique = true, nullable = false)
    private String describe;
}
