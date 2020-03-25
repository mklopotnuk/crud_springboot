package testgroup.crud_springboot.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import testgroup.crud_springboot.model.User;
import testgroup.crud_springboot.security.config.RestServerProperties;
import testgroup.crud_springboot.service.UserService;


import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private RestTemplate restTemplate;
    private String restUrl;

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate, RestServerProperties restServerProperties) {
        this.restTemplate = restTemplate;
        restUrl = "http://" + restServerProperties.getUrl() + ":" + restServerProperties.getPort() + "/";
    }

    @Override
    public List<User> allUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(restUrl+"allusers", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        return responseEntity.getBody();
    }

    @Override
    public void add(User user) {
        restTemplate.postForObject(restUrl + "customer", user, User.class);
    }

    @Override
    public void delete(User user) {
        restTemplate.delete(restUrl + "customer?id="+user.getId().toString());
    }

    @Override
    public void edit(User user) {
        restTemplate.put(restUrl + "customer",user,User.class);
    }

    @Override
    public User getById(Long id) {
        return restTemplate.getForObject (restUrl + "customer?id="+id.toString(), User.class);
    }
}
