package cfm.SoisotaService.controllers;

import cfm.SoisotaService.dto.RoleDataDTO;
import cfm.SoisotaService.services.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
}
