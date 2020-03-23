package testgroup.crud_springboot.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




    @Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("$2a$10$cY.74RdLUV5k65iDDKiSq.FTXi2gFiBoRhMoA86BBV44JUZqrARtm")
                .roles("ADMIN")
                .and()
                .withUser("user")
                .password("$2y$12$q91pvGq9JdfUDm43PorGJev3PWHI84wtgwK23gXp2lFbnoNPtOM5m")
                .roles("USER");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").loginProcessingUrl("/login").permitAll()
                .usernameParameter("name")
                .passwordParameter("password")
                .successHandler(customAuthSuccessHandler)
                .failureHandler(customAuthFailureHandler)
                .and().logout();
    }

}
