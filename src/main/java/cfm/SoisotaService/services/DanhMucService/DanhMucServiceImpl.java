package cfm.SoisotaService.services.DanhMucService;

import cfm.SoisotaService.dto.BankDataDTO;
import cfm.SoisotaService.dto.InvoiceTemplateDataDTO;
import cfm.SoisotaService.dto.PackageDataDTO;
import cfm.SoisotaService.dto.ResponseObjectDTO;
import cfm.SoisotaService.entities.AppBank;
import cfm.SoisotaService.entities.AppInvoiceTemplate;
import cfm.SoisotaService.entities.AppPackage;
import cfm.SoisotaService.repositories.BankRepository;
import cfm.SoisotaService.repositories.InvoiceTemplateRepository;
import cfm.SoisotaService.repositories.PackageRepository;
import cfm.SoisotaService.services.BaseService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service(value = "danhMucService")
@RequiredArgsConstructor
public class DanhMucServiceImpl extends BaseService implements DanhMucService {

  private final ModelMapper modelMapper;
  private final BankRepository bankRepository;
  private final InvoiceTemplateRepository invoiceTemplateRepository;
  private final PackageRepository packageRepository;

  @Override
  public List<AppBank> getAllBank() {
    return bankRepository.findAll();
  }

  @Override
  @Transactional
  public ResponseObjectDTO insertAppBank(BankDataDTO bankDataDTO) {
    //Map thông tin qua đây nhé
    AppBank appBank = modelMapper.map(bankDataDTO, AppBank.class);

    bankRepository.save(appBank);

    //Tạo Bank Code theo quy tắc
    String bankCode = this.generateCode(appBank.getId(), "APBANK");
    appBank.setBankCode(bankCode);

    return new ResponseObjectDTO(true, "Tạo mới ngân hàng thành công", null);
  }

  @Override
  @Transactional
  public ResponseObjectDTO updateAppBank(BankDataDTO bankDataDTO) {
    //Lấy AppBank theo ID
    AppBank appBank = bankRepository.findById(bankDataDTO.getId()).get();

    if (appBank != null) {
      modelMapper.map(bankDataDTO, appBank);

      bankRepository.save(appBank);
    }

    return new ResponseObjectDTO(true, "Cập nhật ngân hàng thành công", null);
  }

  @Override
  @Transactional
  public ResponseObjectDTO deleteAppBank(Long idBank) {
    //Lấy AppBank theo ID
    if (idBank != null) {
      bankRepository.deleteById(idBank);
    }

    return new ResponseObjectDTO(true, "Xóa ngân hàng thành công", null);
  }

  @Override
  @Transactional
  public ResponseObjectDTO deleteListAppBank(List<Long> lstIdBank) {

    if (lstIdBank != null && !lstIdBank.isEmpty()) {
      bankRepository.deleteAllById(lstIdBank);
    }

    return new ResponseObjectDTO(true, "Xóa ngân hàng thành công", null);
  }

  @Override
  public List<AppPackage> getAllPackage() {
    return packageRepository.findAll();
  }

  @Override
  @Transactional
  public ResponseObjectDTO insertAppPackage(PackageDataDTO packageDataDTO) {
    //Map thông tin qua đây nhé
    AppPackage appPackage = modelMapper.map(packageDataDTO, AppPackage.class);

    packageRepository.save(appPackage);

    //Tạo Package Code theo quy tắc
    String packageCode = this.generateCode(appPackage.getId(), "APPACKAGE");
    appPackage.setPackageCode(packageCode);

    return new ResponseObjectDTO(true, "Tạo mới Gói thành công", null);
  }

  @Override
  @Transactional
  public ResponseObjectDTO updateAppPackage(PackageDataDTO packageDataDTO) {
    //Lấy AppPackage theo ID
    AppPackage appPackage = packageRepository.findById(packageDataDTO.getId()).get();

    if (appPackage != null) {
      modelMapper.map(packageDataDTO, appPackage);

      packageRepository.save(appPackage);
    }

    return new ResponseObjectDTO(true, "Cập nhật gói thành công", null);
  }

  @Override
  @Transactional
  public ResponseObjectDTO deleteAppPackage(Long idPackage) {
    if (idPackage != null) {
      packageRepository.deleteById(idPackage);
    }

    return new ResponseObjectDTO(true, "Xóa Gói thành công", null);
  }

  @Override
  @Transactional
  public ResponseObjectDTO deleteListAppPackage(List<Long> lstIdPackage) {
    if (lstIdPackage != null && !lstIdPackage.isEmpty()) {
      bankRepository.deleteAllById(lstIdPackage);
    }

    return new ResponseObjectDTO(true, "Xóa Gói thành công", null);
  }

  @Override
  public List<AppInvoiceTemplate> getAllInvoiceTemplate() {
    return invoiceTemplateRepository.findAll();
  }

  @Override
  @Transactional
  public ResponseObjectDTO insertAppInvoiceTemplate(InvoiceTemplateDataDTO invoiceTemplateDataDTO) {
    //Map thông tin qua đây nhé
    AppInvoiceTemplate appInvoiceTemplate = modelMapper.map(invoiceTemplateDataDTO,
        AppInvoiceTemplate.class);

    invoiceTemplateRepository.save(appInvoiceTemplate);

    //Tạo Invoice Template Code theo quy tắc
    String invoiceTemplateCode = this.generateCode(appInvoiceTemplate.getId(), "APTEMPLATE");
    appInvoiceTemplate.setTemplateCode(invoiceTemplateCode);

    return new ResponseObjectDTO(true, "Tạo mới Mẫu hóa đơn thành công", null);
  }

  @Override
  @Transactional
  public ResponseObjectDTO updateAppInvoiceTemplate(InvoiceTemplateDataDTO invoiceTemplateDataDTO) {
    //Lấy AppPackage theo ID
    AppInvoiceTemplate appInvoiceTemplate = invoiceTemplateRepository.findById(
        invoiceTemplateDataDTO.getId()).get();

    if (appInvoiceTemplate != null) {
      modelMapper.map(invoiceTemplateDataDTO, appInvoiceTemplate);

      invoiceTemplateRepository.save(appInvoiceTemplate);
    }

    return new ResponseObjectDTO(true, "Cập nhật Mẫu hóa đơn thành công", null);
  }

  @Override
  @Transactional
  public ResponseObjectDTO deleteAppInvoiceTemplate(Long idInvoiceTemplate) {
    if (idInvoiceTemplate != null) {
      invoiceTemplateRepository.deleteById(idInvoiceTemplate);
    }

    return new ResponseObjectDTO(true, "Xóa Mẫu hóa đơn thành công", null);
  }

  @Override
  @Transactional
  public ResponseObjectDTO deleteListAppInvoiceTemplate(List<Long> lstIdInvoiceTemplate) {
    if (lstIdInvoiceTemplate != null && !lstIdInvoiceTemplate.isEmpty()) {
      invoiceTemplateRepository.deleteAllById(lstIdInvoiceTemplate);
    }

    return new ResponseObjectDTO(true, "Xóa Mẫu hóa đơn thành công", null);
  }


}
