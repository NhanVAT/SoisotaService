package cfm.SoisotaService.services.DanhMucService;

import cfm.SoisotaService.components.TransformXSLTAndXML;
import cfm.SoisotaService.dto.*;
import cfm.SoisotaService.entities.AppBank;
import cfm.SoisotaService.entities.AppInvoiceTemplate;
import cfm.SoisotaService.entities.AppPackage;
import cfm.SoisotaService.entities.AppSMSEmailTemplate;
import cfm.SoisotaService.models.ResponseFileData;
import cfm.SoisotaService.repositories.BankRepository;
import cfm.SoisotaService.repositories.InvoiceTemplateRepository;
import cfm.SoisotaService.repositories.PackageRepository;
import cfm.SoisotaService.repositories.SmsEmailRepository;
import cfm.SoisotaService.services.BaseService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

@Service(value = "danhMucService")
@RequiredArgsConstructor
public class DanhMucServiceImpl extends BaseService implements DanhMucService {

  private final ModelMapper modelMapper;
  private final BankRepository bankRepository;
  private final InvoiceTemplateRepository invoiceTemplateRepository;
  private final SmsEmailRepository smsEmailRepository;
  private final PackageRepository packageRepository;
  private final TransformXSLTAndXML transformXSLTAndXML;
  private final Gson gson = new GsonBuilder().serializeNulls().create();


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

    // base64 -> byte[] -> blob
//    try {
//      byte[] templateDataByte = Base64.getDecoder().decode(invoiceTemplateDataDTO.getTemplateDataBase64());
////      appInvoiceTemplate.setTemplateData(new SerialBlob(templateDataByte));
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
    byte[] templateDataByte = Base64.getDecoder()
        .decode(invoiceTemplateDataDTO.getTemplateDataBase64());
    appInvoiceTemplate.setTemplateData(templateDataByte);

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

    // base64 -> byte[] -> blob
    byte[] templateDataByte = Base64.getDecoder()
        .decode(invoiceTemplateDataDTO.getTemplateDataBase64());
    appInvoiceTemplate.setTemplateData(templateDataByte);

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

  @Override
  public ResponseFileData getViewInvoiceTemplate(Long idInvoiceTemplate)
      throws JSONException {
    //Laấy mẫu hóa đơn theo ID truyền vao
    AppInvoiceTemplate appInvoiceTemplate = invoiceTemplateRepository.findById(idInvoiceTemplate)
        .get();

    //Tạo data XML của hóa đơn
    DataMBankDTO dataMBankDTO = new DataMBankDTO();
    dataMBankDTO.MA_DDO = "013246546";
    dataMBankDTO.TEN_DDIEN_DVI = "013246546222";
    dataMBankDTO.DUONG_PHO = "0132461231546";
    dataMBankDTO.EMAIL_KH = "013246123546";
    dataMBankDTO.TKHOAN_KH = "0132463rrrr546";
    dataMBankDTO.TEN_DVIQLY_UP_CTREN = "013ffff246546";
    dataMBankDTO.NGAY_CTHANG = "0132ff46546";

    //Từ data XML OBJ => Json
    JSONObject jsDataTempalte = new JSONObject();
    jsDataTempalte.put("TTDCNGCS", new JSONObject(gson.toJson(dataMBankDTO)));

    Document xml = transformXSLTAndXML.jSonToXmlXSL(jsDataTempalte);
    String html = transformXSLTAndXML.transform(xml, appInvoiceTemplate.getTemplateData());
    String result = html.replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>", "");

    return new ResponseFileData(true, "Tạo view Mẫu hóa đơn thành công", result, null);
  }

  public List<AppSMSEmailTemplate> getAllSmsEmailTemplate(){
    return smsEmailRepository.findAll();
  }
  @Transactional
  public ResponseObjectDTO insertAppSmsEmailTemplate(SmsEmaillDataDTO smsEmaillDataDTO){
    // map thong tin qua
    AppSMSEmailTemplate appSMSEmailTemplate = modelMapper.map(smsEmaillDataDTO, AppSMSEmailTemplate.class);
    smsEmailRepository.save(appSMSEmailTemplate);
    String smsEmailCode = this.generateCode(appSMSEmailTemplate.getId(), "APSMSEMAIL");
    appSMSEmailTemplate.setTemplateCode(smsEmailCode);
    return new ResponseObjectDTO(true, "Tạo mới sms email thành công", null);
  }
  @Transactional
  public ResponseObjectDTO updateAppSmsEmailTemplate(SmsEmaillDataDTO smsEmaillDataDTO){
    AppSMSEmailTemplate appSMSEmailTemplate = smsEmailRepository.findById(smsEmaillDataDTO.getId()).get();
    if (appSMSEmailTemplate != null){
      modelMapper.map(smsEmaillDataDTO, appSMSEmailTemplate);
      smsEmailRepository.save(appSMSEmailTemplate);
    }
    return new ResponseObjectDTO(true, "Cập nhật thành công", null);
  }
  @Transactional
  public ResponseObjectDTO deleteAppSmsEmailTemplate(Long id){
    if (id != null) {
      smsEmailRepository.deleteById(id);
    }
    return new ResponseObjectDTO(true, "Xóa thành công ", null);
  }
  @Transactional
  public ResponseObjectDTO deleteListAppSmsEmailTemplate(List<Long> listId){
    if (listId != null && !listId.isEmpty()){
      smsEmailRepository.deleteAllById(listId);
    }
    return new ResponseObjectDTO(true, "Xóa thành công", null);
  }
  public Optional<AppSMSEmailTemplate> getViewSmsEmailTemplate(Long id){
    return smsEmailRepository.findById(id);
  }
}
