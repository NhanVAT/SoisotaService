package cfm.SoisotaService;

import cfm.SoisotaService.entities.AppUser;
import cfm.SoisotaService.services.MenuService;
import cfm.SoisotaService.services.RoleService;
import cfm.SoisotaService.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

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

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
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
