package testgroup.crud_springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import testgroup.crud_springboot.model.Role;
import testgroup.crud_springboot.model.User;
import testgroup.crud_springboot.service.BarcodeService;
import testgroup.crud_springboot.service.RoleService;
import testgroup.crud_springboot.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
public class RestUserController {


    private UserService userService;
    private BarcodeService barcodeService;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RestUserController(UserService userService, BarcodeService barcodeService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.barcodeService = barcodeService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping(value = "/allusers")
    public List<User> allUsers() {
        return userService.allUsers();
    }

    @GetMapping(value = "/customer")
    public User showUser(@RequestParam(name = "id", required = true) Long id) {
        return userService.getById(id);
    }

    @PostMapping(value = "/customer")
    public void addUser(@RequestBody User user) {
        Set<Role> roles=null;

        if (user.getRoles() == null) {
            roles = Collections.singleton(roleService.getRoleById(1L));
        } else{
            roles = user.getRoles();
        }
        user.setRoles(roles);
        userService.add(user);
    }

    @DeleteMapping(path = "/customer")
    public void deleteUser(@RequestParam(name = "id", required = true) Long id) {
        userService.delete(userService.getById(id));
    }

    @PutMapping(path = "/customer")
    public void updateUser(@RequestBody User user){
        User currentUser = userService.getById(user.getId());
        user.setBarcode(currentUser.getBarcode());
        if (user.getPassword().equals("")) {
            user.setPassword(currentUser.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        userService.edit(user);

    }
}
