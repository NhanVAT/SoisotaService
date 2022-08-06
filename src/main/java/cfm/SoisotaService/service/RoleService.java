package cfm.SoisotaService.service;

import cfm.SoisotaService.model.AppRole;
import cfm.SoisotaService.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<AppRole> getAllRole() {
        return roleRepository.findAll();
    }
}
