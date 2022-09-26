package cfm.SoisotaService.repositories;

import cfm.SoisotaService.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<AppRole, Long> {
    boolean existsByRoleId(String roleid);

    AppRole findByRoleId(String roleid);

    @Transactional
    void deleteByRoleId(String roleid);

    AppRole findByRoleKey(String roleKey);

    @Query("select appRole from AppRole appRole where appRole.id in :lstRoleId")
    List<AppRole> findListAppRoleByListId(@Param("lstRoleId") List<Long> lstRoleId);

}
