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

    public AppRole findByRoleId(String name) {
        return roleRepository.findByRoleId(name);
    }


    public void initRoleDefault() {
        AppRole role_1 = new AppRole();
        AppRole role_2 = new AppRole();

        role_1.setRoleId("ROLE_ADMIN");
        role_2.setRoleId("ROLE_USER");

        role_1.setRoleName("Quản trị viên");
        role_2.setRoleName("Người dùng");

        roleRepository.save(role_1);
        roleRepository.save(role_2);
    }
}
