package cfm.SoisotaService.services.MenuService;

import cfm.SoisotaService.dto.MenuDataDTO;
import cfm.SoisotaService.dto.ResponseObjectDTO;
import cfm.SoisotaService.dto.RoleDataDTO;
import cfm.SoisotaService.entities.AppMenu;
import java.util.List;

public interface MenuService {

  List<AppMenu> getAllMenu();

  AppMenu findById(String name);

  List<AppMenu> getListAppMenuByListId(List<Long> lstId);

  void initMenuDefault();

  ResponseObjectDTO insertAppMenu(MenuDataDTO menuDataDTO);

  ResponseObjectDTO updateAppMenu(MenuDataDTO menuDataDTO);

  ResponseObjectDTO deleteAppMenu(Long idMenu);

  ResponseObjectDTO deleteListAppMenu(List<Long> lstIdMenu);
}
