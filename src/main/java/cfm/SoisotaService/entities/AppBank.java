package cfm.SoisotaService.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "app_back")
public class AppBank extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "bank_code", nullable = false)
  private String bankCode;

  @Column(name = "bank_name", nullable = false)
  private String bankName;

  @Column(name = "bank_type", nullable = false)
  private String bankType;

  @Column(name = "bank_describe")
  private String bankDescribe;
}

