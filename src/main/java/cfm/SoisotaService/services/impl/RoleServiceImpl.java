package cfm.SoisotaService.services.impl;

import cfm.SoisotaService.entities.AppMenu;
import cfm.SoisotaService.entities.AppRole;
import cfm.SoisotaService.repositories.RoleRepository;
import cfm.SoisotaService.services.MenuService;
import cfm.SoisotaService.services.RoleService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  @Autowired
  private final RoleRepository roleRepository;

  @Autowired
  private final MenuService menuService;

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
    role_1.setActive(true);

    AppMenu appMenu_1 = menuService.findById("1");
    Set<AppMenu> appMenus_1 = new HashSet<>();
    appMenus_1.add(appMenu_1);

    role_1.setMenus(appMenus_1);

    roleRepository.save(role_1);

    AppRole role_2 = new AppRole();
    role_2.setRoleId("ROLE_USER");
    role_2.setRoleName("Người dùng");
    role_2.setCreatedBy("admin");
    role_2.setActive(true);

    AppMenu appMenu_2 = menuService.findById("2");
    Set<AppMenu> appMenus_2 = new HashSet<>();
    appMenus_2.add(appMenu_2);

    role_2.setMenus(appMenus_2);

    roleRepository.save(role_2);
  }

}
