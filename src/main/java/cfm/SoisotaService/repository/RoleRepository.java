package cfm.SoisotaService.repository;

import cfm.SoisotaService.model.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface RoleRepository extends JpaRepository<AppRole, Integer> {
    boolean existsByName(String username);

    AppRole findByName(String username);

    @Transactional
    void deleteByName(String username);

}
