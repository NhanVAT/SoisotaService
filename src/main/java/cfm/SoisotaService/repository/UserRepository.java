package cfm.SoisotaService.repository;

import cfm.SoisotaService.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    boolean existsByUserName(String username);

    AppUser findByUserName(String username);

    @Transactional
    void deleteByUserName(String username);

}
