package cfm.SoisotaService.repository;

import cfm.SoisotaService.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoleRepository extends JpaRepository<AppRole, Long> {
    boolean existsByRoleId(String roleid);

    AppRole findByRoleId(String roleid);

    @Transactional
    void deleteByRoleId(String roleid);

}
