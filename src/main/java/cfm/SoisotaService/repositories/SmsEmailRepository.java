package cfm.SoisotaService.repositories;


import cfm.SoisotaService.entities.AppSMSEmailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SmsEmailRepository extends JpaRepository<AppSMSEmailTemplate, Long> {
  Optional<AppSMSEmailTemplate> findById(Long id);
}
