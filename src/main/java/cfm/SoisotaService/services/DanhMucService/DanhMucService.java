package cfm.SoisotaService.services.DanhMucService;

import cfm.SoisotaService.dto.*;
import cfm.SoisotaService.entities.AppBank;
import cfm.SoisotaService.entities.AppInvoiceTemplate;
import cfm.SoisotaService.entities.AppPackage;
import cfm.SoisotaService.entities.AppSMSEmailTemplate;
import cfm.SoisotaService.models.ResponseFileData;
import org.json.JSONException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface DanhMucService {

  List<AppBank> getAllBank();

  ResponseObjectDTO insertAppBank(BankDataDTO bankDataDTO);

  ResponseObjectDTO updateAppBank(BankDataDTO bankDataDTO);

  ResponseObjectDTO deleteAppBank(Long idBank);

  ResponseObjectDTO deleteListAppBank(List<Long> lstIdBank);

  List<AppPackage> getAllPackage();

  ResponseObjectDTO insertAppPackage(PackageDataDTO packageDataDTO);

  ResponseObjectDTO updateAppPackage(PackageDataDTO packageDataDTO);

  ResponseObjectDTO deleteAppPackage(Long idPackage);

  ResponseObjectDTO deleteListAppPackage(List<Long> lstIdPackage);

  List<AppInvoiceTemplate> getAllInvoiceTemplate();

  ResponseObjectDTO insertAppInvoiceTemplate(InvoiceTemplateDataDTO invoiceTemplateDataDTO);

  ResponseObjectDTO updateAppInvoiceTemplate(InvoiceTemplateDataDTO invoiceTemplateDataDTO);

  ResponseObjectDTO deleteAppInvoiceTemplate(Long idInvoiceTemplate);

  ResponseObjectDTO deleteListAppInvoiceTemplate(List<Long> lstIdInvoiceTemplate);

  ResponseFileData getViewInvoiceTemplate(Long idInvoiceTemplate) throws JSONException;
  List<AppSMSEmailTemplate> getAllSmsEmailTemplate();
  ResponseObjectDTO insertAppSmsEmailTemplate(SmsEmaillDataDTO smsEmaillDataDTO);
  ResponseObjectDTO updateAppSmsEmailTemplate(SmsEmaillDataDTO smsEmaillDataDTO);
  ResponseObjectDTO deleteAppSmsEmailTemplate(Long id);
  ResponseObjectDTO deleteListAppSmsEmailTemplate(List<Long> listId);
  Optional<AppSMSEmailTemplate> getViewSmsEmailTemplate(Long id);
}
