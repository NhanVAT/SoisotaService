package cfm.SoisotaService.dto;

import java.time.Instant;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PackageDataDTO {

  private Long id;
  private String packageCode;
  private String packageName;
  private String packageType;
  private Long packageEffectiveTime;
  private Long packageMaximumInvoice;
  private String packageDescribe;
  private Boolean active;
  @NotEmpty(message = "Thiếu createdBy")
  private String createdBy;
  @NotEmpty(message = "Thiếu createdDate")
  private Instant createdDate;
  private String lastModifiedBy;
  private Instant lastModifiedDate;
}
