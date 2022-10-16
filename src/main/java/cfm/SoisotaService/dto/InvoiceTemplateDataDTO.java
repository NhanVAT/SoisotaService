package cfm.SoisotaService.dto;

import java.io.File;
import java.time.Instant;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
  @NotNull(message = "Thiếu createdBy")
  private String createdBy;
  @NotNull(message = "Thiếu createdDate")
  private Instant createdDate;
  private String lastModifiedBy;
  private Instant lastModifiedDate;
  private String templateDataBase64;
}
