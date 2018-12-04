package info.ciolek.starter.vaadin.spring.config;

import info.ciolek.starter.vaadin.spring.util.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() // CSRF handled by Vaadin
                .exceptionHandling()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                .and()
                .logout().logoutSuccessUrl("/login/loggedout")
                .and()
                .authorizeRequests()
                .antMatchers("/", "/VAADIN/**", "/HEARTBEAT/**", "/UIDL/**", "/resources/**"
                        , "/login", "/login**", "/login/**", "/manifest.json", "/icons/**", "/images/**",
                        // (development mode) static resources
                        "/frontend/**",
                        // (production mode) static resources
                        "/frontend-es5/**", "/frontend-es6/**").permitAll()
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
                // deny other URLs until authenticated
                .anyRequest().fullyAuthenticated();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User
                .withUsername("user")
                .password(encoder().encode("userPass"))
                .roles("USER").build());
        manager.createUser(User
                .withUsername("admin")
                .password(encoder().encode("adminPass"))
                .roles("ADMIN").build());
        return manager;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }
}