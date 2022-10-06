package cfm.SoisotaService.dto;

import java.time.Instant;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BankDataDTO {

  private Long id;
  @NotEmpty(message = "Thiếu bankCode")
  private String bankCode;
  @NotEmpty(message = "Thiếu bankName")
  private String bankName;
  @NotEmpty(message = "Thiếu bankType")
  private String bankType;
  private String bankDescribe;
  private Boolean active;
  @NotEmpty(message = "Thiếu createdBy")
  private String createdBy;
  @NotEmpty(message = "Thiếu createdDate")
  private Instant createdDate;
  private String lastModifiedBy;
  private Instant lastModifiedDate;
}
