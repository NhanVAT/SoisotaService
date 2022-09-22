package cfm.SoisotaService.controllers;

import cfm.SoisotaService.dto.MenuDataDTO;
import cfm.SoisotaService.dto.ResponseObjectDTO;
import cfm.SoisotaService.dto.RoleDataDTO;
import cfm.SoisotaService.models.RegisterRoleUser;
import cfm.SoisotaService.services.MenuService;
import cfm.SoisotaService.services.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/quanTriHeThong")
@Api(tags = "quanTriHeThong")
@RequiredArgsConstructor
public class QuanTriHeThongController {

  private final ModelMapper modelMapper;
  private final RoleService roleService;
  private final MenuService menuService;

  @GetMapping(value = "/getAllRole")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Get All Role", response = RoleDataDTO.class, responseContainer = "List",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public List<RoleDataDTO> getAllRole() {
    return roleService.getAllRole().stream().map(role -> modelMapper.map(role, RoleDataDTO.class))
        .collect(Collectors.toList());
  }

  @GetMapping(value = "/getAllMenu")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Get All Menu", response = MenuDataDTO.class, responseContainer = "List",
      authorizations = {@Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public List<MenuDataDTO> getAllMenu() {
    return menuService.getAllMenu().stream().map(menu -> modelMapper.map(menu, MenuDataDTO.class))
        .collect(Collectors.toList());
  }

  @PostMapping("/insertAppRole")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Insert AppRole", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> insertAppRole(
      @Valid @RequestBody RoleDataDTO roleDataDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(roleService.insertAppRole(roleDataDTO));
  }

  @PutMapping("/updateAppRole")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Update AppRole", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> updateAppRole(
      @Valid @RequestBody RoleDataDTO roleDataDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(roleService.updateAppRole(roleDataDTO));
  }

  @PostMapping(value = "/deleteAppRole")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Delete AppRole", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The role doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> deleteAppRole(
      @Valid @RequestBody Long idRole) {
    return ResponseEntity.status(HttpStatus.OK).body(roleService.deleteAppRole(idRole));
  }

  @PostMapping(value = "/deleteListAppRole")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "Delete list AppRole", response = ResponseEntity.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The role doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public ResponseEntity<ResponseObjectDTO> deleteListAppRole(
      @Valid @RequestBody List<Long> lstIdRole) {
    return ResponseEntity.status(HttpStatus.OK).body(roleService.deleteListAppRole(lstIdRole));
  }
}
