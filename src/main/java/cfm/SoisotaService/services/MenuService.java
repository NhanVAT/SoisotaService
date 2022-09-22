package cfm.SoisotaService.services;

import cfm.SoisotaService.entities.AppMenu;
import java.util.List;

public interface MenuService {

  List<AppMenu> getAllMenu();

  AppMenu findById(String name);

  List<AppMenu> getListAppMenuByListId(List<Long> lstId);

  void initMenuDefault();
}
