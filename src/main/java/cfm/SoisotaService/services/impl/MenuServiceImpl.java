package cfm.SoisotaService.services.impl;

import cfm.SoisotaService.dto.MenuDataDTO;
import cfm.SoisotaService.dto.ResponseObjectDTO;
import cfm.SoisotaService.entities.AppMenu;
import cfm.SoisotaService.repositories.MenuRepository;
import cfm.SoisotaService.services.MenuService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "menuService")
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

  @Autowired
  private final MenuRepository menuRepository;

  @Autowired
  private final ModelMapper modelMapper;

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

  @Override
  @Transactional
  public ResponseObjectDTO insertAppMenu(MenuDataDTO menuDataDTO) {
    //Map thông tin AppMenu ở đây luôn
    AppMenu appMenu = modelMapper.map(menuDataDTO, AppMenu.class);

    menuRepository.save(appMenu);

    //Check xem là menu cha hay con
    //+ Nếu menu cha thì nếu chưa có menu con thì Menu_Id se != -1
    List<AppMenu> lstAllAppMenuChild = menuRepository.findAllByMenuParent(appMenu.getId());
    if (lstAllAppMenuChild == null || lstAllAppMenuChild.isEmpty()) {
      appMenu.setMenuId(appMenu.getId().toString());
    }

    //+ Nếu menu con thì đi
    if (appMenu.getMenuParent() != 0l) {
      // - Cập nhật menu_id = id
      appMenu.setMenuId(appMenu.getId().toString());

      //- tìm thằng menu cha và cập nhật lại menu_id = -1 (nghĩa là menu cha đó có tồn tại con)
      AppMenu appMenuParent = menuRepository.findById(appMenu.getMenuParent()).get();

      if (appMenuParent != null) {
        //Nếu có = -1
        appMenuParent.setMenuId("-1");
      }

      menuRepository.save(appMenuParent);
    }

    return new ResponseObjectDTO(true, "Tạo mới menu thành công", null);
  }

  @Override
  @Transactional
  public ResponseObjectDTO updateAppMenu(MenuDataDTO menuDataDTO) {
    //Lấy AppMenu theo ID
    AppMenu appMenu = menuRepository.findById(menuDataDTO.getId()).get();

    if (appMenu != null) {
      modelMapper.map(menuDataDTO, appMenu);

      if (appMenu.getMenuParent() != 0l) {
        // - Cập nhật menu_id = id trước đã rồi lát check xem nó có con thì rồi gán lại = -1
        appMenu.setMenuId(appMenu.getId().toString());

        //- tìm thằng menu cha và cập nhật lại menu_id = -1 (nghĩa là menu cha đó có tồn tại con)
        AppMenu appMenuParent = menuRepository.findById(appMenu.getMenuParent()).get();

        if (appMenuParent != null) {
          appMenuParent.setMenuId("-1");
        }

        menuRepository.save(appMenuParent);
      }

      //Kiểm tra xem thằng củ nồi xem nó có thằng nào là con không nếu có thì gán menu_id cuả nó = -1
      List<AppMenu> lstAppMenuChild = menuRepository.findAllByMenuParent(appMenu.getId());

      if (lstAppMenuChild != null && !lstAppMenuChild.isEmpty()) {
        appMenu.setMenuId("-1");
      }

      menuRepository.save(appMenu);
    }

    return new ResponseObjectDTO(true, "Cập nhật menu thành công", null);
  }

  @Override
  @Transactional
  public ResponseObjectDTO deleteAppMenu(Long idMenu) {
    //Lấy thằng cũ ra nào
    List<AppMenu> lstAllRemove = new ArrayList<>();

    Optional<AppMenu> OPAppMenu = menuRepository.findById(idMenu);

    if (OPAppMenu != null && !OPAppMenu.isEmpty()) {
      AppMenu appMenu = OPAppMenu.get();

      //Thêm thằng cha vào để lát xóa
      lstAllRemove.add(appMenu);

      //Lấy all menu để đệ quy tìm tat cả con cháu của nó
      List<AppMenu> lstAllAppMenu = menuRepository.findAll();
      if (lstAllAppMenu != null && !lstAllAppMenu.isEmpty()) {

        //Lấy list id của con và cháu của nó rồi mới xóa hết 1 lần nhé
        List<AppMenu> lstAllAppMenuChild = this.getListAllMenuChildByIdParent(lstAllAppMenu,
            idMenu);

        //Nếu có thì add vào lst để xóa
        if (lstAllAppMenuChild != null && !lstAllAppMenuChild.isEmpty()) {
          lstAllRemove.addAll(lstAllAppMenuChild);
        }
      }

      //Xóa thôi
      menuRepository.deleteAll(lstAllRemove);
    }

    return new ResponseObjectDTO(true, "Xóa menu thành công", null);
  }

  @Override
  @Transactional
  public ResponseObjectDTO deleteListAppMenu(List<Long> lstIdMenu) {
    //xóa tương tự xóa từng thằng
    for (Long idMenu : lstIdMenu) {
      List<AppMenu> lstAllRemove = new ArrayList<>();

      Optional<AppMenu> OPAppMenu = menuRepository.findById(idMenu);

      if (OPAppMenu != null && !OPAppMenu.isEmpty()) {
        AppMenu appMenu = OPAppMenu.get();

        //Thêm thằng cha vào để lát xóa
        lstAllRemove.add(appMenu);

        //Lấy all menu để đệ quy tìm tat cả con cháu của nó
        List<AppMenu> lstAllAppMenu = menuRepository.findAll();
        if (lstAllAppMenu != null && !lstAllAppMenu.isEmpty()) {

          //Lấy list id của con và cháu của nó rồi mới xóa hết 1 lần nhé
          List<AppMenu> lstAllAppMenuChild = this.getListAllMenuChildByIdParent(lstAllAppMenu,
              idMenu);

          //Nếu có thì add vào lst để xóa
          if (lstAllAppMenuChild != null && !lstAllAppMenuChild.isEmpty()) {
            lstAllRemove.addAll(lstAllAppMenuChild);
          }
        }

        //Xóa thôi
        menuRepository.deleteAll(lstAllRemove);
      }
    }

    return new ResponseObjectDTO(true, "Xóa menu thành công", null);
  }

  public List<AppMenu> getListAllMenuChildByIdParent(List<AppMenu> lstAllAppMenu, Long idParent) {
    List<AppMenu> result = new ArrayList<>();

    List<AppMenu> lstChild = lstAllAppMenu.stream().filter(menu -> menu.getMenuParent() == idParent)
        .collect(Collectors.toList());

    if (lstChild != null && !lstChild.isEmpty()) {
      result.addAll(lstChild);

      for (AppMenu itemMenuChild : lstChild) {
        result.addAll(this.getListAllMenuChildByIdParent(lstAllAppMenu, itemMenuChild.getId()));
      }
    }

    return result;
  }
}
