package testgroup.crud_springboot.dao;


import testgroup.crud_springboot.model.Role;

import java.util.List;

public interface RoleDAO {

    Role getById(Long id);
    void add(Role role);
    void delete(Long id);
    void edit(Role role);
    List<Role> allRoles();


}
