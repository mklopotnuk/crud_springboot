package testgroup.crud_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import testgroup.crud_springboot.dao.UserDAO;
import testgroup.crud_springboot.model.User;
import testgroup.crud_springboot.service.UserService;


import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private RestTemplate restTemplate;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> allUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange("http://127.0.0.1:8081/allusers", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        return responseEntity.getBody();
    }

    @Override
    public void add(User user) {
        restTemplate.postForObject("http://127.0.0.1:8081/customer", user, User.class);
    }

    @Override
    public void delete(User user) {
        restTemplate.delete("http://127.0.0.1:8081/customer?id="+user.getId().toString());
    }

    @Override
    public void edit(User user) {
        restTemplate.put("http://127.0.0.1:8081/customer",user,User.class);
    }

    @Override
    public User getById(Long id) {
        return restTemplate.getForObject ("http://127.0.0.1:8081/customer?id="+id.toString(), User.class);
    }
}
