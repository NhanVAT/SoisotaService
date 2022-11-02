package cfm.SoisotaService.repositories;

import cfm.SoisotaService.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    boolean existsByUserName(String username);

    AppUser findByUserName(String username);

    @Transactional
    void deleteByUserName(String username);

    boolean existsByEmail(String email);

    boolean existsByUserNameAndIdIsNotLike(String username, Long id);

    boolean existsByEmailAndIdIsNot(String email, Long id);

    boolean existsByPhoneAndIdIsNot(String phone, Long id);

    boolean existsByPhone(String phone);

    AppUser findByUserNameAndActiveIsTrue(String username);

}
