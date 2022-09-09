package cfm.SoisotaService.service;

import cfm.SoisotaService.exception.CustomException;
import cfm.SoisotaService.model.AppRole;
import cfm.SoisotaService.model.AppUser;
import cfm.SoisotaService.repository.UserRepository;
import cfm.SoisotaService.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;

    public String signin(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findByUserName(username).getRoles().stream().collect(Collectors.toList()));
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public String signup(AppUser appUser) {
        if (!userRepository.existsByUserName(appUser.getUserName())) {
            appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
            userRepository.save(appUser);
            return jwtTokenProvider.createToken(appUser.getUserName(), appUser.getRoles().stream().collect(Collectors.toList()));
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void delete(String username) {
        userRepository.deleteByUserName(username);
    }

    public AppUser search(String username) {
        AppUser appUser = userRepository.findByUserName(username);
        if (appUser == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return appUser;
    }

    public AppUser whoami(HttpServletRequest req) {
        return userRepository.findByUserName(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
    }

    public String refresh(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByUserName(username).getRoles().stream().collect(Collectors.toList()));
    }

    public List<AppUser> getAllUser() {
        return userRepository.findAll();
    }

    public void initUserDefault() {
        AppUser admin = new AppUser();
        admin.setUserName("admin");
        admin.setPassword("admin");
        admin.setEmail("admin@email.com");
        admin.setActive(true);

        AppRole roleAdmin = roleService.findByRoleId("ROLE_ADMIN");
        Set<AppRole> roleSetAdmin = new HashSet<>();
        roleSetAdmin.add(roleAdmin);

        admin.setRoles(roleSetAdmin);

        this.signup(admin);

        AppUser client = new AppUser();
        client.setUserName("client");
        client.setPassword("client");
        client.setEmail("client@email.com");
        client.setActive(true);

        AppRole roleUser = roleService.findByRoleId("ROLE_USER");
        Set<AppRole> roleSetUser = new HashSet<>();
        roleSetUser.add(roleUser);

        client.setRoles(roleSetUser);

        this.signup(client);
    }
}
