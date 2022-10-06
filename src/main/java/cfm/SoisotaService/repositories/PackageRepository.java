package cfm.SoisotaService.repositories;

import cfm.SoisotaService.entities.AppPackage;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<AppPackage, Long> {

  Optional<AppPackage> findById(Long id);
}
