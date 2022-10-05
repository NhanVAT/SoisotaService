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
@Table(name = "app_invoice_template")
public class AppInvoiceTemplate extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "template_code", nullable = false)
  private String templateCode;

  @Column(name = "template_name", nullable = false)
  private String templateName;

  @Column(name = "template_type", nullable = false)
  private String templateType;

  @Column(name = "template_describe")
  private String templateDescribe;

  @Column(name = "template_data")
  private byte[] templateData;
}

