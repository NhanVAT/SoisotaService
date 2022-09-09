package cfm.SoisotaService.services;

import cfm.SoisotaService.entities.AppUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {
    String signin(String username, String password);

    String signup(AppUser appUser);

    void delete(String username);

    AppUser search(String username);

    AppUser getInfoCurrentUser(HttpServletRequest req);

    String refresh(String username);

    List<AppUser> getAllUser();

    void initUserDefault();
}
