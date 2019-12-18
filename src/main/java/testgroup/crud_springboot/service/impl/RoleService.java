package testgroup.crud_springboot.service.impl;

import org.springframework.stereotype.Service;
import testgroup.crud_springboot.model.Role;


import org.springframework.beans.factory.annotation.Autowired;
import testgroup.crud_springboot.dao.RoleDAO;

import java.util.List;

@Service
public class RoleService implements  testgroup.crud_springboot.service.RoleService{
      private RoleDAO roleDAO;

    @Autowired
    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDAO.getById(id);
    }

    @Override
    public void updateRole(Role role) {
        roleDAO.edit(role);
    }

    @Override
    public void addRole(Role role) {
        roleDAO.add(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleDAO.delete(id);
    }

    @Override
    public List<Role> getRoles() {
        return roleDAO.allRoles();
    }
}
