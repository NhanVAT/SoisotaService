package cfm.SoisotaService.services.RoleService;

import cfm.SoisotaService.dto.ResponseObjectDTO;
import cfm.SoisotaService.dto.RoleDataDTO;
import cfm.SoisotaService.entities.AppMenu;
import cfm.SoisotaService.entities.AppRole;
import cfm.SoisotaService.repositories.RoleRepository;
import cfm.SoisotaService.services.MenuService.MenuService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  @Autowired
  private final RoleRepository roleRepository;
  @Autowired
  private final MenuService menuService;
  @Autowired
  private final ModelMapper modelMapper;


  public List<AppRole> getAllRole() {
    List<AppRole> result = roleRepository.findAll();

    return result;
  }

  public AppRole findByRoleId(String name) {
    return roleRepository.findByRoleId(name);
  }

  public AppRole getByRoleKey(String roleKey) {
    return roleRepository.findByRoleKey(roleKey);
  }

  @Transactional
  public void initRoleDefault() {
    List<AppMenu> lstAllAppMenu = menuService.getAllMenu();

    AppRole role_1 = new AppRole();
    role_1.setRoleId("ROLE_ADMIN");
    role_1.setRoleKey("ROLE_ADMIN");
    role_1.setRoleName("Nhóm quyền quản trị viên");
    role_1.setRoleDescribe("Nhóm quyền quản trị viên");
    role_1.setCreatedBy("admin");
    role_1.setActive(true);

    Set<AppMenu> setAppMenus = new HashSet<>();
    setAppMenus.addAll(lstAllAppMenu);

    role_1.setMenus(setAppMenus);

    roleRepository.save(role_1);

    AppRole role_2 = new AppRole();
    role_2.setRoleId("ROLE_USER");
    role_2.setRoleKey("ROLE_USER");
    role_2.setRoleName("Nhóm quyền người dùng");
    role_2.setRoleDescribe("Nhóm quyền người dùng");
    role_2.setCreatedBy("admin");
    role_2.setActive(true);

    role_2.setMenus(setAppMenus);

    roleRepository.save(role_2);
  }

  @Transactional
  public ResponseObjectDTO insertAppRole(RoleDataDTO roleDataDTO) {
    //Map thông tin AppRole và AppMenu ở đây luôn
    AppRole appRole = modelMapper.map(roleDataDTO, AppRole.class);

    //Lấy thêm list menu theo list ID Menu để gán
    List<AppMenu> appMenuList = menuService.getListAppMenuByListId(roleDataDTO.getLstMenuId());

    Set<AppMenu> setAppMenus = new HashSet<>();
    setAppMenus.addAll(appMenuList);

    appRole.setMenus(setAppMenus);

    roleRepository.save(appRole);

    return new ResponseObjectDTO(true, "Tạo mới role thành công", null);
  }

  @Transactional
  public ResponseObjectDTO updateAppRole(RoleDataDTO roleDataDTO) {
    //Lấy thanh niên cũ theo ID ra nào
    AppRole appRole = roleRepository.findById(roleDataDTO.getId()).get();

    if (appRole != null) {
      //Map thông tin AppRole và AppMenu ở đây luôn
      modelMapper.map(roleDataDTO, appRole);

      //Lấy thêm list menu theo list ID Menu để gán
      List<AppMenu> appMenuList = menuService.getListAppMenuByListId(roleDataDTO.getLstMenuId());

      Set<AppMenu> setAppMenus = new HashSet<>();
      setAppMenus.addAll(appMenuList);

      appRole.setMenus(setAppMenus);

      roleRepository.save(appRole);
    }

    return new ResponseObjectDTO(true, "Cập nhật role thành công", null);
  }

  @Transactional
  public ResponseObjectDTO deleteAppRole(Long idRole) {
    //Lấy thanh niên cũ theo ID ra nào
    roleRepository.deleteById(idRole);

    return new ResponseObjectDTO(true, "Xóa role thành công", null);
  }

  @Transactional
  public ResponseObjectDTO deleteListAppRole(List<Long> lstIdRole) {
    //Lấy thanh niên cũ theo ID ra nào
    roleRepository.deleteAllById(lstIdRole);

    return new ResponseObjectDTO(true, "Xóa list role thành công", null);
  }

  @Transactional
  public List<AppRole> getListAppRoleByListId(List<Long> lstAppRoleId) {
    return roleRepository.findListAppRoleByListId(lstAppRoleId);
  }

}
