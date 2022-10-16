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
@Table(name = "app_smsemail_template")
public class AppSMSEmailTemplate extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "template_code")
  private String templateCode;

  @Column(name = "template_name", nullable = false)
  private String templateName;

  @Column(name = "template_type", nullable = false)
  private String templateType;

  @Size(min = 1, max = 8000)
  @Column(name = "template_content")
  private String templateContent;

  @Size(min = 4, max = 5000)
  @Column(name = "template_describe")
  private String templateDescribe;
}

