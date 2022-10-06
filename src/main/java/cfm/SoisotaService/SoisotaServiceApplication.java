package cfm.SoisotaService;

import cfm.SoisotaService.services.MenuService.MenuService;
import cfm.SoisotaService.services.RoleService.RoleService;
import cfm.SoisotaService.services.UserService.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties
@EntityScan(basePackages = {"cfm.SoisotaService.entities"})
public class SoisotaServiceApplication implements CommandLineRunner {

  @Autowired
  final UserService userService;
  @Autowired
  final RoleService roleService;
  @Autowired
  final MenuService menuService;

  public static void main(String[] args) {
    SpringApplication.run(SoisotaServiceApplication.class, args);
  }

  @Override
  public void run(String... params) throws Exception {
    // Add menu mặc định
    if (menuService.getAllMenu().isEmpty()) {
      menuService.initMenuDefault();
    }

    // Add role mặc định
    if (roleService.getAllRole().isEmpty()) {
      roleService.initRoleDefault();
    }

    // Add User mặc định
    if (userService.getAllUser().isEmpty()) {
      userService.initUserDefault();
    }
    //add comment
  }
}
