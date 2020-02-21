package testgroup.crud_springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import testgroup.crud_springboot.model.Barcode;
import testgroup.crud_springboot.model.Role;
import testgroup.crud_springboot.model.User;
import testgroup.crud_springboot.service.BarcodeService;
import testgroup.crud_springboot.service.RoleService;
import testgroup.crud_springboot.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    private UserService userService;
    private BarcodeService barcodeService;
    private RoleService roleService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserController(UserService userService, BarcodeService barcodeService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.barcodeService = barcodeService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping(value = "/admin")
    public String allUsers() {
        return "admin";
    }

    @GetMapping(value = "/admin/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        Long barcodeId = user.getBarcode().getId();
        model.addAttribute("user", user);
        model.addAttribute("barcodeId", barcodeId);
        return "edit";
    }

    @PostMapping(value = "/admin/edit")
    public String editUser(User user, HttpServletRequest request) {
        User currentUser = userService.getById(user.getId());
        user.setBarcode(currentUser.getBarcode());

        if (user.getPassword().equals("")) {
            user.setPassword(currentUser.getPassword());
        } else {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        Set<Role> roleSet = Collections.singleton(roleService.getRoleById(Long.valueOf(request.getParameter("role"))));
        user.setRoles(roleSet);
        userService.edit(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/add")
    public String addPage(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }

    @GetMapping(value = "/login")
    public String loginPage(String logout, Model model) {
        if (logout != null) {
            model.addAttribute("message", "Logout successfully");
        }
        return "login";
    }


    @PostMapping(value = "add")
    public String add(@ModelAttribute("user") User user, BindingResult bindingResult, HttpServletRequest request) {
        String role = request.getParameter("role");
        Set<Role> roles;
        if (role == null) {
            roles = Collections.singleton(roleService.getRoleById(1L));
        } else {
            roles = Collections.singleton(roleService.getRoleById(Long.valueOf(role)));
        }
        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/admin/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        userService.delete(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/user/view/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        //Тут есть косяк, если ввести не существующий id пользователя, покажется страничка с пустыми строками
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long currentId = currentUser.getId();
        Set<Role> currentRoles = currentUser.getRoles();
// Вот этот кусок кода нормальный? отсюда
        final boolean[] isAdmin = {false};
        currentRoles.forEach((e) -> {
            if (e.getName().equals("ADMIN")) {
                isAdmin[0] = !isAdmin[0];
            }
        });
//        до сюда,  мне он просто не нравится.
        if (currentId.equals(id) || isAdmin[0]) {
            User user = userService.getById(id);
            model.addAttribute("user", user);
            try {
                Barcode barcode = barcodeService.getById(user.getBarcode().getId());
                model.addAttribute("barcode", barcode);
            } catch (NullPointerException e) {
                return "redirect:/admin";
            }
            return "showUser";
        } else {
            return "redirect:/user/view/" + currentId;
        }
    }

}
