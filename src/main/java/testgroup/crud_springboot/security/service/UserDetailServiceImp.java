package testgroup.crud_springboot.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import testgroup.crud_springboot.model.User;
import testgroup.crud_springboot.service.UserService;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailServiceImp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);

        if(user==null){
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return user;
    }
}
