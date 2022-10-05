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
@Table(name = "app_invoice_price")
public class AppInvoicePrice extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "invoice_code", nullable = false)
  private String invoiceCode;

  @Column(name = "invoice_name", nullable = false)
  private String invoiceName;

  @Column(name = "invoice_type", nullable = false)
  private String invoiceType;

  @Column(name = "invoice_price", nullable = false)
  private Long invoicePrice;

  @Column(name = "invoice_promotion")
  private Long invoicePromotion;

  @Column(name = "invoice_describe")
  private String invoiceDescribe;
}

