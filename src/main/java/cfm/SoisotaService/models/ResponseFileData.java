package cfm.SoisotaService.models;

import lombok.Data;

@Data
public class ResponseFileData {

  private boolean success;
  private String message;
  private String html;
  private String pdf;

  public ResponseFileData(boolean success, String message, String html, String pdf) {
    this.success = success;
    this.message = message;
    this.html = html;
    this.pdf = pdf;
  }
}
