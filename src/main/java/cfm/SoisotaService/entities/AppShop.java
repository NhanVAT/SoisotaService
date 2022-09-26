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
@Table(name = "app_shop")
public class AppShop extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "shop_name", nullable = false)
  private String shopName;

  @Column(name = "shop_user_name", nullable = false)
  private String shopUserName;

  @Column(name = "shop_owner_name", nullable = false)
  private String shopOwnerName;

  @Column(name = "shop_type", nullable = false)
  private Integer shopType;

  @Column(name = "shop_describe")
  private String shopDescribe;

  @Column(name = "shop_likes")
  private Long shopLikes;

  @Column(name = "ma_tinh", nullable = false)
  private String maTinh;

  @Column(name = "ma_huyen", nullable = false)
  private String maHuyen;

  @Column(name = "ma_xa", nullable = false)
  private String maXa;

  @Column(name = "address")
  private String address;

  @Column(name = "shop_phone")
  private String shopPhone;

  @Column(name = "shop_rating")
  private Float shopRating;

  @Column(name = "shop_image")
  private byte[] shopImage;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
  @JoinTable(name = "app_shop_products", joinColumns = {
      @JoinColumn(name = "shop_id")}, inverseJoinColumns = {@JoinColumn(name = "product_id")})
  private Set<AppProduct> products;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
  @JoinTable(name = "app_shop_reviews", joinColumns = {
      @JoinColumn(name = "shop_id")}, inverseJoinColumns = {@JoinColumn(name = "review_id")})
  private Set<AppReview> reviews;
}
