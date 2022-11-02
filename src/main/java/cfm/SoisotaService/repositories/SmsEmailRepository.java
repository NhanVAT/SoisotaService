package cfm.SoisotaService.repositories;


import cfm.SoisotaService.entities.AppSMSEmailTemplate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmsEmailRepository extends JpaRepository<AppSMSEmailTemplate, Long> {

  Optional<AppSMSEmailTemplate> findById(Long id);

  Optional<AppSMSEmailTemplate> findByTemplateCodeAndTemplateTypeAndActiveIsTrue(String code,
      String type);
}
