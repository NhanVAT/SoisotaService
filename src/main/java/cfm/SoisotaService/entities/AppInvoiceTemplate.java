package cfm.SoisotaService.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Entity
@Data
@Table(name = "app_invoice_template")
@XmlRootElement(name = "appivoicetemplate")
@XmlAccessorType(XmlAccessType.FIELD)
public class AppInvoiceTemplate extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @XmlElement
  private Long id;

  @Column(name = "template_code")
  @XmlElement
  private String templateCode;

  @Column(name = "template_name", nullable = false)
  @XmlElement
  private String templateName;

  @Column(name = "template_type", nullable = false)
  @XmlElement
  private String templateType;

  @Column(name = "template_describe")
  private String templateDescribe;

  @Column(name = "template_data")
  @Lob
  private byte[] templateData;

  public AppInvoiceTemplate() {
  }
}

