package cfm.SoisotaService.controllers;

import cfm.SoisotaService.dto.ResponseObjectDTO;
import cfm.SoisotaService.dto.UserDataDTO;
import cfm.SoisotaService.dto.UserResponseDTO;
import cfm.SoisotaService.entities.AppUser;
import cfm.SoisotaService.models.AuthToken;
import cfm.SoisotaService.models.LoginUser;
import cfm.SoisotaService.models.RegisterRoleUser;
import cfm.SoisotaService.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
@Api(tags = "users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final ModelMapper modelMapper;

  @PostMapping("/signin")
  @ApiOperation(value = "${UserController.signin}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 422, message = "Invalid username/password supplied")})
  public ResponseEntity<?> login(@RequestBody LoginUser loginUser) {
    final String token = userService.signin(loginUser);
    return ResponseEntity.ok(new AuthToken(token));
  }

  @PostMapping("/signup")
  @ApiOperation(value = "${UserController.signup}")
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 422, message = "Username is already in use")})
  public String signup(@ApiParam("Signup User") @RequestBody UserDataDTO user) {
    return userService.signup(modelMapper.map(user, AppUser.class));
  }

  @DeleteMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "${UserController.delete}", authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public String delete(@ApiParam("Username") @PathVariable String username) {
    userService.delete(username);
    return username;
  }

  @GetMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @ApiOperation(value = "${UserController.search}", response = UserResponseDTO.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 404, message = "The user doesn't exist"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public UserResponseDTO search(@ApiParam("Username") @PathVariable String username) {
    return modelMapper.map(userService.search(username), UserResponseDTO.class);
  }

  @GetMapping(value = "/getInfoCurrentUser")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  @ApiOperation(value = "${UserController.getInfoCurrentUser}", response = UserResponseDTO.class, authorizations = {
      @Authorization(value = "apiKey")})
  @ApiResponses(value = {//
      @ApiResponse(code = 400, message = "Something went wrong"), //
      @ApiResponse(code = 403, message = "Access denied"), //
      @ApiResponse(code = 500, message = "Expired or invalid JWT token")})
  public UserResponseDTO getInfoCurrentUser(HttpServletRequest req) {
    return modelMapper.map(userService.getInfoCurrentUser(req), UserResponseDTO.class);
  }

  @GetMapping("/refresh")
  @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
  public String refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }

  @PostMapping("/register")
  @ApiOperation(value = "${UserController.register}")
  public ResponseEntity<ResponseObjectDTO> register(@RequestBody RegisterRoleUser registerRoleUser){
    ModelMapper modelMapper = new ModelMapper();
    modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT);

    AppUser appUser = modelMapper.map(registerRoleUser, AppUser.class);
    String username = userService.register(appUser, registerRoleUser);
    return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObjectDTO(true, "Reigister New Account Success", username)
    );
  }

}
