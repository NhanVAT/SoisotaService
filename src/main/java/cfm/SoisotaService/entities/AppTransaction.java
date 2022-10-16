package cfm.SoisotaService.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "app_transaction")
public class AppTransaction extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "transaction_code", nullable = false)
  private String transactionCode;

  @Column(name = "transaction_name", nullable = false)
  private String transactionName;

  @Column(name = "transaction_type", nullable = false)
  private String transactionType;

  @Column(name = "transaction_money", nullable = false)
  private Long transactionMoney;

  @Column(name = "transaction_Time")
  private Long transactionTime;

  @Column(name = "transaction_describe")
  private String transactionDescribe;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
  @JoinTable(name = "app_transaction_images", joinColumns = {
      @JoinColumn(name = "transaction_id")}, inverseJoinColumns = {@JoinColumn(name = "image_id")})
  private Set<AppImage> images;
}
