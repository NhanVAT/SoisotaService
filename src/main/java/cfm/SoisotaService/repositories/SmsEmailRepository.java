package cfm.SoisotaService.repositories;

import cfm.SoisotaService.entities.AppSmsEmail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SmsEmailRepository extends JpaRepository<AppSmsEmail, Long> {
  Optional<AppSmsEmail> findById(Long id);
}
