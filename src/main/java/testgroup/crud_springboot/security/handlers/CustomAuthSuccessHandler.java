package testgroup.crud_springboot.security.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import testgroup.crud_springboot.dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
    private UserDAO userDAO;

    @Autowired
    public CustomAuthSuccessHandler(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException, IOException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if(roles.contains("ADMIN")){
            httpServletResponse.sendRedirect("/admin");
        }else{
            String name = authentication.getName();
            Long id = userDAO.findByUserName(name).getId();
            httpServletResponse.sendRedirect("/user/view/"+id);
        }
    }
}
