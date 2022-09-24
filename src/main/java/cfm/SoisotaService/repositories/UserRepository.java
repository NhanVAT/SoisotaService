package cfm.SoisotaService.repositories;

import cfm.SoisotaService.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    boolean existsByUserName(String username);

    AppUser findByUserName(String username);

    @Transactional
    void deleteByUserName(String username);

//    @Query(value = "SELECT * FROM USERS u WHERE u.status = 1",
//            nativeQuery = true)
    boolean existsByEmail(String email);



}
