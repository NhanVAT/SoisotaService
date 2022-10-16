package cfm.SoisotaService.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@NoArgsConstructor
@Table( name = "app_smsemail_template")
public class AppSmsEmail extends AbstractAuditingEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "template_code", nullable = false)
  private String templateCode;

  @Column(name = "template_content")
  private String templateContent;

  @Column(name = "template_describe")
  private String templateDescribe;

  @Column(name= "template_name", nullable = false)
  private String templateName;

  @Column(name ="template_type", nullable = false)
  private String templateType;
}
