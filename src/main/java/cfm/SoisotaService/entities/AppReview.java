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
@Table(name = "app_review")
public class AppReview extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "review_user_name", nullable = false)
  private String reviewUserName;

  @Column(name = "review_content", nullable = false)
  private String reviewContent;

  @Column(name = "review_parent")
  private Long reviewParent;

  @Column(name = "review_rating")
  private Float reviewRating;

  @Column(name = "review_likes")
  private Long reviewLikes;

  @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
  @JoinTable(name = "app_review_images", joinColumns = {
      @JoinColumn(name = "review_id")}, inverseJoinColumns = {@JoinColumn(name = "image_id")})
  private Set<AppImage> images;
}
