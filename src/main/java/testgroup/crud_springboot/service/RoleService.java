package testgroup.crud_springboot.service;

import testgroup.crud_springboot.model.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(Long id);

    void updateRole(Role role);

    void addRole(Role role);

    void deleteRole(Long id);

    List<Role> getRoles();
}
