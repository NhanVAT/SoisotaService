package cfm.SoisotaService.repositories;

import cfm.SoisotaService.entities.AppBank;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BankRepository extends JpaRepository<AppBank, Long> {

  Optional<AppBank> findById(Long id);
}
