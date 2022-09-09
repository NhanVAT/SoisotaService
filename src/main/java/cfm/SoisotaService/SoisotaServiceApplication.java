package cfm.SoisotaService;

import cfm.SoisotaService.service.RoleService;
import cfm.SoisotaService.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties
@EntityScan(basePackages = {"cfm.SoisotaService.model"})
public class SoisotaServiceApplication implements CommandLineRunner {

    final UserService userService;

    final RoleService roleService;

    public static void main(String[] args) {
        SpringApplication.run(SoisotaServiceApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... params) throws Exception {
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
