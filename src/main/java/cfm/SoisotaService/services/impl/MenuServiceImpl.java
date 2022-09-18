package cfm.SoisotaService.services.impl;

import cfm.SoisotaService.entities.AppMenu;
import cfm.SoisotaService.repositories.MenuRepository;
import cfm.SoisotaService.services.MenuService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "menuService")
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

  @Autowired
  private final MenuRepository menuRepository;

  @Override
  public List<AppMenu> getAllMenu() {
    return menuRepository.findAll();
  }

  @Override
  public AppMenu findById(String id) {
    return menuRepository.findById(Long.parseLong(id)).get();
  }

  @Override
  public void initMenuDefault() {
    AppMenu appMenu_1 = new AppMenu();
    appMenu_1.setMenuId("-1");
    appMenu_1.setMenuParent(0l);
    appMenu_1.setMenuOrder(1l);
    appMenu_1.setMenuName("Quản trị người dùng");
    appMenu_1.setMenuRouterLink("/QuanTriNguoiDung");
    appMenu_1.setCreatedBy("admin");
    appMenu_1.setActive(true);
    menuRepository.save(appMenu_1);

    AppMenu appMenu_2 = new AppMenu();
    appMenu_2.setMenuId("-1");
    appMenu_2.setMenuParent(0l);
    appMenu_2.setMenuOrder(2l);
    appMenu_2.setMenuName("Quản trị phân quyền");
    appMenu_2.setMenuRouterLink("/QuanTriPhanQuyen");
    appMenu_2.setCreatedBy("admin");
    appMenu_2.setActive(true);

    menuRepository.save(appMenu_2);
  }
}
