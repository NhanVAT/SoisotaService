package cfm.SoisotaService.repositories;

import cfm.SoisotaService.entities.AppUser;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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

  AppUser findByPhoneAndActiveIsTrue(String phone);

  AppUser findByEmailAndActiveIsTrue(String phone);

}
