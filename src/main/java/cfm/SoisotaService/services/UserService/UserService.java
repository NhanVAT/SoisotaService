package cfm.SoisotaService.services.UserService;

import cfm.SoisotaService.dto.ResponseObjectDTO;
import cfm.SoisotaService.dto.UserDataDTO;
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

    String register(AppUser appUser, RegisterRoleUser registerRoleUser) ;

    ResponseObjectDTO insertAppUser(UserDataDTO userDataDTO);

    ResponseObjectDTO updateAppUser(UserDataDTO userDataDTO);

    ResponseObjectDTO deleteListAppUser(List<Long> lstIdUser);

    ResponseObjectDTO checkEmail(String email);

}
