package cfm.SoisotaService.services.impl;

import cfm.SoisotaService.entities.AppMenu;
import cfm.SoisotaService.repositories.MenuRepository;
import cfm.SoisotaService.services.MenuService;
import java.util.List;
import javax.transaction.Transactional;
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

  public List<AppMenu> getListAppMenuByListId(List<Long> lstId) {
    return menuRepository.findListAppMenuByListId(lstId);
  }

  @Transactional
  public void initMenuDefault() {
    //Tạo menu Cha
    AppMenu appMenu_Father = new AppMenu();
    appMenu_Father.setMenuId("-1");
    appMenu_Father.setMenuParent(0l);
    appMenu_Father.setMenuOrder(1l);
    appMenu_Father.setMenuName("Quản trị hệ thống");
    appMenu_Father.setMenuPrjName("QTriHThong");
    appMenu_Father.setMenuRouterLink("");
    appMenu_Father.setMenuIcon("pi-th-large");
    appMenu_Father.setCreatedBy("admin");
    appMenu_Father.setActive(true);
    menuRepository.save(appMenu_Father);

    AppMenu appMenu_Child_1 = new AppMenu();
    appMenu_Child_1.setMenuId("1");
    appMenu_Child_1.setMenuParent(appMenu_Father.getId());
    appMenu_Child_1.setMenuOrder(1l);
    appMenu_Child_1.setMenuName("Quản trị người dùng");
    appMenu_Child_1.setMenuPrjName("QTriHThong");
    appMenu_Child_1.setMenuRouterLink("/QuanTriNguoiDung");
    appMenu_Child_1.setMenuIcon("pi-users");
    appMenu_Child_1.setCreatedBy("admin");
    appMenu_Child_1.setActive(true);
    menuRepository.save(appMenu_Child_1);

    //Gán lại menu ID
    appMenu_Child_1.setMenuId(appMenu_Child_1.getId().toString());

    AppMenu appMenu_Child_2 = new AppMenu();
    appMenu_Child_2.setMenuId("1");
    appMenu_Child_2.setMenuParent(appMenu_Father.getId());
    appMenu_Child_2.setMenuOrder(2l);
    appMenu_Child_2.setMenuName("Quản trị phân quyền");
    appMenu_Child_2.setMenuPrjName("QTriHThong");
    appMenu_Child_2.setMenuRouterLink("/QuanTriPhanQuyen");
    appMenu_Child_2.setMenuIcon("pi-user-edit");
    appMenu_Child_2.setCreatedBy("admin");
    appMenu_Child_2.setActive(true);

    menuRepository.save(appMenu_Child_2);

    //Gán lại menu ID
    appMenu_Child_2.setMenuId(appMenu_Child_2.getId().toString());

    AppMenu appMenu_Child_3 = new AppMenu();
    appMenu_Child_3.setMenuId("1");
    appMenu_Child_3.setMenuParent(appMenu_Father.getId());
    appMenu_Child_3.setMenuOrder(3l);
    appMenu_Child_3.setMenuName("Quản trị menu");
    appMenu_Child_3.setMenuPrjName("QTriHThong");
    appMenu_Child_3.setMenuRouterLink("/QuanTriMenu");
    appMenu_Child_3.setMenuIcon("pi-bars");
    appMenu_Child_3.setCreatedBy("admin");
    appMenu_Child_3.setActive(true);

    menuRepository.save(appMenu_Child_3);

    //Gán lại menu ID
    appMenu_Child_3.setMenuId(appMenu_Child_3.getId().toString());
  }
}
