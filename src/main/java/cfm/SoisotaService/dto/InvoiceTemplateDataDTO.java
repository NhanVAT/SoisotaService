package cfm.SoisotaService.dto;

import java.time.Instant;
import javax.validation.constraints.NotNull;
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
  private String viewTemplateDataHTML;
  private Boolean active;
  @NotNull(message = "Thiếu createdBy")
  private String createdBy;
  @NotNull(message = "Thiếu createdDate")
  private Instant createdDate;
  private String lastModifiedBy;
  private Instant lastModifiedDate;
  private String templateDataBase64;
}
