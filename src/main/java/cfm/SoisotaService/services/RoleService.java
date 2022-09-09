package cfm.SoisotaService.services;

import cfm.SoisotaService.entities.AppRole;

import java.util.List;

public interface RoleService {

    List<AppRole> getAllRole();

    AppRole findByRoleId(String name);

    void initRoleDefault();
}
