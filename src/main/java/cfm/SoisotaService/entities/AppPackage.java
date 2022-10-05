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
@Table(name = "app_package")
public class AppPackage extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "package_code", nullable = false)
  private String packageCode;

  @Column(name = "package_name", nullable = false)
  private String packageName;

  @Column(name = "package_type", nullable = false)
  private String packageType;

  @Column(name = "package_effective_time", nullable = false)
  private Long packageEffectiveTime;

  @Column(name = "package_maximum_invoice", nullable = false)
  private Long packageMaximumInvoice;

  @Column(name = "package_describe")
  private String packageDescribe;
}

