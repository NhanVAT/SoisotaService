package cfm.SoisotaService.service;

import cfm.SoisotaService.model.AppRole;
import cfm.SoisotaService.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<AppRole> getAllRole() {
        return roleRepository.findAll();
    }

    public void initRoleDefault() {
        AppRole role_1 = new AppRole();
        AppRole role_2 = new AppRole();

        role_1.setName("ROLE_ADMIN");
        role_2.setName("ROLE_CLIENT");

        roleRepository.save(role_1);
        roleRepository.save(role_2);
    }
}
