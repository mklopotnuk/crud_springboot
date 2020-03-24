package testgroup.crud_springboot.service;

import testgroup.crud_springboot.model.User;

import java.util.List;

public interface UserService {

    List<User> allUsers();

    void add(User user);

    void delete(User user);

    void edit(User user);

    User getById(Long id);
}
