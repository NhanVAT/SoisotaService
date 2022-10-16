package cfm.SoisotaService.dto;

import cfm.SoisotaService.entities.AbstractAuditingEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor

public class SmsEmaillDataDTO {

  private Long id;
  @NotEmpty(message = "Thiếu templateCode")
  private String templateCode;
  private String templateContent;
  private String templateDescribe;
  @NotEmpty(message = "Thiếu templateName")
  private String templateName;
  @NotEmpty(message = "Thiếu templateType")
  private String templateType;
  private boolean active;
  @NotEmpty(message = "Thiếu createdBy")
  private String createdBy;
  @NotEmpty(message = "Thiếu createdDate")
  private Instant createdDate;
  private String lastModifiedBy;
  private Instant lastModifiedDate;


}
