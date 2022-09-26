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
@Table(name = "app_product")
public class AppProduct extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "product_name", nullable = false)
  private String productName;

  @Column(name = "product_type", nullable = false)
  private String productType;

  @Column(name = "product_describe")
  private String productDescribe;

  @Column(name = "product_price")
  private String productPrice;

  @Column(name = "product_rating")
  private Float productRating;

  @Column(name = "product_likes")
  private Long productLikes;

  @Column(name = "product_image")
  private byte[] productImage;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
  @JoinTable(name = "app_product_reviews", joinColumns = {
      @JoinColumn(name = "shop_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
  private Set<AppProduct> products;

}
