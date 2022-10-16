package cfm.SoisotaService.controllers;

import cfm.SoisotaService.dto.BankDataDTO;
import cfm.SoisotaService.dto.InvoiceTemplateDataDTO;
import cfm.SoisotaService.dto.PackageDataDTO;
import cfm.SoisotaService.dto.ResponseObjectDTO;
import cfm.SoisotaService.models.ResponseFileData;
import cfm.SoisotaService.services.DanhMucService.DanhMucService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/danhMuc")
@Api(tags = "danhMuc")
@RequiredArgsConstructor
public class DanhMucController {

  private final ModelMapper modelMapper;
  private final DanhMucService danhMucService;

  @GetMapping(value = "/getAllBank")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Get All Bank", response = BankDataDTO.class, responseContainer = "List",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public List<BankDataDTO> getAllBank() {
    return danhMucService.getAllBank().stream()
        .map(mank -> modelMapper.map(mank, BankDataDTO.class)).collect(Collectors.toList());
  }

  @PostMapping("/insertAppBank")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Insert AppBank", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> insertAppBank(
      @Valid @RequestBody BankDataDTO bankDataDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(danhMucService.insertAppBank(bankDataDTO));
  }

  @PutMapping("/updateAppBank")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Update AppBank", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> updateAppBank(
      @Valid @RequestBody BankDataDTO bankDataDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(danhMucService.updateAppBank(bankDataDTO));
  }

  @PostMapping(value = "/deleteAppBank")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Delete AppBank", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The role doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> deleteAppBank(
      @Valid @RequestBody Long idBank) {
    return ResponseEntity.status(HttpStatus.OK).body(danhMucService.deleteAppBank(idBank));
  }

  @PostMapping(value = "/deleteListAppBank")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Delete list AppBank", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The role doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> deleteListAppBank(
      @Valid @RequestBody List<Long> lstIdBank) {
    return ResponseEntity.status(HttpStatus.OK).body(danhMucService.deleteListAppBank(lstIdBank));
  }

  @GetMapping(value = "/getAllPackage")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Get All Package", response = PackageDataDTO.class, responseContainer = "List",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public List<PackageDataDTO> getAllPackage() {
    return danhMucService.getAllPackage().stream()
        .map(mank -> modelMapper.map(mank, PackageDataDTO.class)).collect(Collectors.toList());
  }

  @PostMapping("/insertAppPackage")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Insert AppPackage", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> insertAppPackage(
      @Valid @RequestBody PackageDataDTO bankDataDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(danhMucService.insertAppPackage(bankDataDTO));
  }

  @PutMapping("/updateAppPackage")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Update AppPackage", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> updateAppPackage(
      @Valid @RequestBody PackageDataDTO bankDataDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(danhMucService.updateAppPackage(bankDataDTO));
  }

  @PostMapping(value = "/deleteAppPackage")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Delete AppPackage", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The role doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> deleteAppPackage(
      @Valid @RequestBody Long idPackage) {
    return ResponseEntity.status(HttpStatus.OK).body(danhMucService.deleteAppPackage(idPackage));
  }

  @PostMapping(value = "/deleteListAppPackage")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Delete list AppPackage", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The role doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> deleteListAppPackage(
      @Valid @RequestBody List<Long> lstIdPackage) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(danhMucService.deleteListAppPackage(lstIdPackage));
  }

  @GetMapping(value = "/getAllInvoiceTemplate")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Get All InvoiceTemplate", response = InvoiceTemplateDataDTO.class, responseContainer = "List",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public List<InvoiceTemplateDataDTO> getAllInvoiceTemplate() {
    return danhMucService.getAllInvoiceTemplate().stream()
        .map(mank -> modelMapper.map(mank, InvoiceTemplateDataDTO.class))
        .collect(Collectors.toList());
  }

  @PostMapping("/insertAppInvoiceTemplate")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Insert AppInvoiceTemplate", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> insertAppInvoiceTemplate(
      @Valid @RequestBody InvoiceTemplateDataDTO appInvoice
  ) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(danhMucService.insertAppInvoiceTemplate(appInvoice));
  }

  @PutMapping("/updateAppInvoiceTemplate")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Update AppInvoiceTemplate", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> updateAppInvoiceTemplate(
      @Valid @RequestBody InvoiceTemplateDataDTO bankDataDTO) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(danhMucService.updateAppInvoiceTemplate(bankDataDTO));
  }

  @PostMapping(value = "/deleteAppInvoiceTemplate")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Delete AppInvoiceTemplate", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The role doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> deleteAppInvoiceTemplate(
      @Valid @RequestBody Long idInvoiceTemplate) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(danhMucService.deleteAppInvoiceTemplate(idInvoiceTemplate));
  }

  @PostMapping(value = "/deleteListAppInvoiceTemplate")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Delete list AppInvoiceTemplate", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The role doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> deleteListAppInvoiceTemplate(
      @Valid @RequestBody List<Long> lstIdInvoiceTemplate) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(danhMucService.deleteListAppInvoiceTemplate(lstIdInvoiceTemplate));
  }

  @PostMapping(value = "/getViewInvoiceTemplate")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Get View AppInvoiceTemplate", response = ResponseFileData.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The role doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseFileData getViewInvoiceTemplate(@Valid @RequestBody Long idInvoiceTemplate)
      throws JSONException {
    return danhMucService.getViewInvoiceTemplate(idInvoiceTemplate);
  }
}
