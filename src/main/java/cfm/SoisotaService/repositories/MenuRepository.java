package cfm.SoisotaService.repositories;

import cfm.SoisotaService.entities.AppMenu;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MenuRepository extends JpaRepository<AppMenu, Long> {

  Optional<AppMenu> findById(Long id);

  @Query("select appMenu from AppMenu appMenu where appMenu.id in :lstId")
  List<AppMenu> findListAppMenuByListId(@Param("lstId") List<Long> lstId);

  List<AppMenu> findAllByMenuParent(Long id);
}
