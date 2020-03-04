package testgroup.crud_springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import testgroup.crud_springboot.security.handlers.CustomAuthFailureHandler;
import testgroup.crud_springboot.security.handlers.CustomAuthSuccessHandler;
import testgroup.crud_springboot.service.impl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private CustomAuthSuccessHandler customAuthSuccessHandler;
    private CustomAuthFailureHandler customAuthFailureHandler;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityConfiguration(CustomAuthSuccessHandler customAuthSuccessHandler, CustomAuthFailureHandler customAuthFailureHandler, UserDetailsServiceImpl userDetailsService) {
        this.customAuthSuccessHandler = customAuthSuccessHandler;
        this.customAuthFailureHandler = customAuthFailureHandler;
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/add").permitAll()
//                .antMatchers("/user/**").hasAnyAuthority("USER,ADMIN")
//                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login").permitAll()
                .usernameParameter("name")
                .passwordParameter("password")
                .successHandler(customAuthSuccessHandler)
                .failureHandler(customAuthFailureHandler)
                .and()
                .logout()
                .and().csrf().disable();

    }

}
