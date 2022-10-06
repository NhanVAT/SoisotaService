package cfm.SoisotaService.services.RoleService;

import cfm.SoisotaService.dto.ResponseObjectDTO;
import cfm.SoisotaService.dto.RoleDataDTO;
import cfm.SoisotaService.entities.AppRole;
import java.util.List;

public interface RoleService {

  List<AppRole> getAllRole();

  AppRole findByRoleId(String name);

  AppRole getByRoleKey(String roleKey);

  void initRoleDefault();

  ResponseObjectDTO insertAppRole(RoleDataDTO roleDataDTO);

  ResponseObjectDTO updateAppRole(RoleDataDTO roleDataDTO);

  ResponseObjectDTO deleteAppRole(Long idRole);

  ResponseObjectDTO deleteListAppRole(List<Long> lstIdRole);

  List<AppRole> getListAppRoleByListId(List<Long>  lstAppRoleId);
}
