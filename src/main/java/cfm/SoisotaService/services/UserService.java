package cfm.SoisotaService.services;

import cfm.SoisotaService.entities.AppUser;
import cfm.SoisotaService.models.LoginUser;
import cfm.SoisotaService.models.RegisterRoleUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    String signin(LoginUser loginUser);

    String signup(AppUser appUser);

    void delete(String username);

    AppUser search(String username);

    AppUser getInfoCurrentUser(HttpServletRequest req);

    String refresh(String username);

    List<AppUser> getAllUser();

    void initUserDefault();

    String register(RegisterRoleUser registerRoleUser) ;
}
