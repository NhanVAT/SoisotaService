package cfm.SoisotaService.services.impl;

import cfm.SoisotaService.dto.ResponseObjectDTO;
import cfm.SoisotaService.dto.UserDataDTO;
import cfm.SoisotaService.entities.AppMenu;
import cfm.SoisotaService.entities.AppRole;
import cfm.SoisotaService.entities.AppUser;
import cfm.SoisotaService.exception.CustomException;
import cfm.SoisotaService.models.LoginUser;
import cfm.SoisotaService.models.RegisterRoleUser;
import cfm.SoisotaService.repositories.UserRepository;
import cfm.SoisotaService.security.JwtTokenProvider;
import cfm.SoisotaService.services.MenuService;
import cfm.SoisotaService.services.RoleService;
import cfm.SoisotaService.services.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service(value = "userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  @Autowired
  private final UserRepository userRepository;
  @Autowired
  private final PasswordEncoder passwordEncoder;
  @Autowired
  private final JwtTokenProvider jwtTokenProvider;
  @Autowired
  private final AuthenticationManager authenticationManager;
  @Autowired
  private final RoleService roleService;

  @Autowired
  private final MenuService menuService;
  @Autowired
  private final ModelMapper modelMapper;

  public String signin(LoginUser loginUser) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginUser.getUserName(),
              loginUser.getPassword()));

      return jwtTokenProvider.createToken(loginUser.getUserName(),
          userRepository.findByUserName(loginUser.getUserName()).getRoles().stream()
              .collect(Collectors.toList()));
    } catch (AuthenticationException e) {
      throw new CustomException("Invalid username/password supplied",
          HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String signup(AppUser appUser) {
    if (!userRepository.existsByUserName(appUser.getUserName())) {
      appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
      userRepository.save(appUser);
      return jwtTokenProvider.createToken(appUser.getUserName(),
          appUser.getRoles().stream().collect(Collectors.toList()));
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public String register(AppUser appUser, RegisterRoleUser registerRoleUser) {

    //check exist username
    if (!userRepository.existsByUserName(appUser.getUserName())) {
      //check exist email
      if(!userRepository.existsByEmail(appUser.getEmail())){
      //check password vs confirm password
      if(appUser.getPassword().equalsIgnoreCase(registerRoleUser.getConfirmPassword())){
          appUser.setActive(false);
          appUser.setCreatedBy("user");
          appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

          //set role: ROLE_USER
          AppRole appRole = roleService.findByRoleId("ROLE_USER");
          Set<AppRole> roleSet = new HashSet<>();
          roleSet.add(appRole);

          appUser.setRoles(roleSet);

          userRepository.save(appUser);
          return appUser.getUserName();
        }
          throw new CustomException("Confirm password and password must be same ", HttpStatus.UNPROCESSABLE_ENTITY);
      }
        throw new CustomException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
  }

  public void delete(String username) {
    userRepository.deleteByUserName(username);
  }

  public AppUser search(String username) {
    AppUser appUser = userRepository.findByUserName(username);
    if (appUser == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return appUser;
  }

  public AppUser getInfoCurrentUser(HttpServletRequest req) {
    return userRepository
        .findByUserName(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public String refresh(String username) {
    return jwtTokenProvider.createToken(username,
        userRepository.findByUserName(username).getRoles().stream().collect(Collectors.toList()));
  }

  public List<AppUser> getAllUser() {
    return userRepository.findAll();
  }

  public void initUserDefault() {



    AppUser admin = new AppUser();
    admin.setUserId("admin");
    admin.setUserName("admin");
    admin.setPassword("admin");
    admin.setEmail("admin@email.com");
    admin.setActive(true);
    admin.setCreatedBy("admin");

    AppRole roleAdmin = roleService.findByRoleId("ROLE_ADMIN");
    Set<AppRole> roleSetAdmin = new HashSet<>();
    roleSetAdmin.add(roleAdmin);

    admin.setRoles(roleSetAdmin);

    this.signup(admin);

    AppUser client = new AppUser();
    client.setUserId("user");
    client.setUserName("user");
    client.setPassword("user");
    client.setEmail("user@email.com");
    client.setActive(true);
    client.setCreatedBy("admin");

    AppRole roleUser = roleService.findByRoleId("ROLE_USER");
    Set<AppRole> roleSetUser = new HashSet<>();
    roleSetUser.add(roleUser);

    client.setRoles(roleSetUser);

    this.signup(client);
  }


  public ResponseObjectDTO updateAppUser(UserDataDTO userDataDTO) {
    AppUser appUser = userRepository.findById(userDataDTO.getId()).orElseThrow(() ->
      new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND)
    );

    modelMapper.map(userDataDTO, appUser);
    userRepository.save(appUser);
    return new ResponseObjectDTO(true, "Cập nhật người dùng thành công", null);
  }

  public ResponseObjectDTO insertAppUser(UserDataDTO userDataDTO) {
    AppUser appUser = modelMapper.map(userDataDTO, AppUser.class);

    if (!userRepository.existsByUserName(appUser.getUserName())) {
      if (!userRepository.existsByEmail(appUser.getEmail())) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);
        return new ResponseObjectDTO(true, "Thêm người dùng mới thành công", null);
      }
      throw new CustomException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
    throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
  }

  public ResponseObjectDTO deleteListAppUser(List<Long> lstIdUser){
    userRepository.deleteAllById(lstIdUser);
    return new ResponseObjectDTO(true, "Xoá list người dùng thành công", null);
  }
}
