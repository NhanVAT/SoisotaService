package cfm.SoisotaService.services.DanhMucService;

import cfm.SoisotaService.dto.BankDataDTO;
import cfm.SoisotaService.dto.InvoiceTemplateDataDTO;
import cfm.SoisotaService.dto.PackageDataDTO;
import cfm.SoisotaService.dto.ResponseObjectDTO;
import cfm.SoisotaService.entities.AppBank;
import cfm.SoisotaService.entities.AppInvoiceTemplate;
import cfm.SoisotaService.entities.AppPackage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

  ResponseObjectDTO getViewInvoiceTemplate(Long id);
}
