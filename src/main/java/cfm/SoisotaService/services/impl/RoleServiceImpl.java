package cfm.SoisotaService.services.impl;

import cfm.SoisotaService.entities.AppRole;
import cfm.SoisotaService.repositories.RoleRepository;
import cfm.SoisotaService.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "roleService")
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    @Autowired
    private final RoleRepository roleRepository;

    public List<AppRole> getAllRole() {
        return roleRepository.findAll();
    }

    public AppRole findByRoleId(String name) {
        return roleRepository.findByRoleId(name);
    }

    public void initRoleDefault() {
        AppRole role_1 = new AppRole();
        role_1.setRoleId("ROLE_ADMIN");
        role_1.setRoleName("Quản trị viên");
        role_1.setCreatedBy("admin");

        roleRepository.save(role_1);

        AppRole role_2 = new AppRole();
        role_2.setRoleId("ROLE_USER");
        role_2.setRoleName("Người dùng");
        role_2.setCreatedBy("admin");

        roleRepository.save(role_2);
    }

}
