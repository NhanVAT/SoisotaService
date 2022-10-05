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
@Table(name = "app_image")
public class AppImage extends AbstractAuditingEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Size(min = 1, max = 20, message = "Max id role length: 20 characters")
  @Column(name = "image_code", nullable = false)
  private String imageCode;

  @Size(min = 1, max = 50, message = "Max name role length: 50 characters")
  @Column(name = "image_name", nullable = false)
  private String imageName;

  @Column(name = "image_describe")
  private String imageDescribe;

  @Column(name = "image_data")
  private byte[] imageData;
}
