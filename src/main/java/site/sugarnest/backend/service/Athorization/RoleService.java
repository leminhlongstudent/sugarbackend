package site.sugarnest.backend.service.Athorization;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.sugarnest.backend.dto.request.RoleRequest;
import site.sugarnest.backend.dto.response.RoleResponse;
import site.sugarnest.backend.mapper.IRoleMapper;
import site.sugarnest.backend.reponsitoties.IPermissionRepository;
import site.sugarnest.backend.reponsitoties.IRoleRepository;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class RoleService {
    IRoleRepository iRoleRepository;
    IPermissionRepository iPermissionRepository;
    IRoleMapper iRoleMapper;

    public RoleResponse create(RoleRequest request) {
        var role = iRoleMapper.toRole(request);
        var permission = iPermissionRepository.findAllById(request.getPermissions());
        role.setPermissions(new HashSet<>(permission));
        role = iRoleRepository.save(role);
        return iRoleMapper.toRoleResponse(role);
    }

    public List<RoleResponse> getAll() {
        var roles = iRoleRepository.findAll();
        return roles.stream().map(iRoleMapper::toRoleResponse).toList();
    }
    public void delete(String name) {
        iRoleRepository.deleteById(name);
    }
}
