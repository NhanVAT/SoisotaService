package cfm.SoisotaService.services.impl;

import cfm.SoisotaService.entities.AppMenu;
import cfm.SoisotaService.entities.AppRole;
import cfm.SoisotaService.repositories.RoleRepository;
import cfm.SoisotaService.services.MenuService;
import cfm.SoisotaService.services.RoleService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
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
    List<AppRole> result = roleRepository.findAll();

    return result;
  }

  public AppRole findByRoleId(String name) {
    return roleRepository.findByRoleId(name);
  }

  @Transactional
  public void initRoleDefault() {
    List<AppMenu> lstAllAppMenu = menuService.getAllMenu();

    AppRole role_1 = new AppRole();
    role_1.setRoleId("ROLE_ADMIN");
    role_1.setRoleName("Quản trị viên");
    role_1.setCreatedBy("admin");
    role_1.setActive(true);

    Set<AppMenu> setAppMenus = new HashSet<>();
    setAppMenus.addAll(lstAllAppMenu);

    role_1.setMenus(setAppMenus);

    roleRepository.save(role_1);

    AppRole role_2 = new AppRole();
    role_2.setRoleId("ROLE_USER");
    role_2.setRoleName("Người dùng");
    role_2.setCreatedBy("admin");
    role_2.setActive(true);

    role_2.setMenus(setAppMenus);

    roleRepository.save(role_2);
  }

}
