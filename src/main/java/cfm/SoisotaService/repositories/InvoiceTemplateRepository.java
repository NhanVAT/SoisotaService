package cfm.SoisotaService.repositories;

import cfm.SoisotaService.entities.AppInvoiceTemplate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceTemplateRepository extends JpaRepository<AppInvoiceTemplate, Long> {

  Optional<AppInvoiceTemplate> findById(Long id);
}
