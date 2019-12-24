package testgroup.crud_springboot.dao.impl;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import testgroup.crud_springboot.dao.RoleDAO;
import testgroup.crud_springboot.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RoleDAOImpl implements RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.find(Role.class, id));
    }

    @Override
    public void edit(Role role) {
        entityManager.merge(role);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> allRoles() {
        return entityManager.createQuery("from Role").getResultList();
    }
}
