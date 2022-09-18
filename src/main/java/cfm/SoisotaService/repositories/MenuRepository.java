package cfm.SoisotaService.repositories;

import cfm.SoisotaService.entities.AppMenu;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<AppMenu, Long> {

  Optional<AppMenu> findById(Long id);

}
