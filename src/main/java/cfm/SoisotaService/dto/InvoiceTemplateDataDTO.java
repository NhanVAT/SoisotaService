package cfm.SoisotaService.dto;

import java.time.Instant;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InvoiceTemplateDataDTO {

  private Long id;
  private String templateCode;
  private String templateName;
  private String templateType;
  private String templateDescribe;
  private byte[] templateData;
  private Boolean active;
  @NotEmpty(message = "Thiếu createdBy")
  private String createdBy;
  @NotEmpty(message = "Thiếu createdDate")
  private Instant createdDate;
  private String lastModifiedBy;
  private Instant lastModifiedDate;
}
