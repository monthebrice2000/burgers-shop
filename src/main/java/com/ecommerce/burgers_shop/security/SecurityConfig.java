package com.ecommerce.burgers_shop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ecommerce.burgers_shop.models.User;
import com.ecommerce.burgers_shop.repository.UserRepository;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    // @Autowired
    // private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null)
                return user;
            throw new UsernameNotFoundException("User '" + username + "' not found");
        };
    }

    @SuppressWarnings("deprecation")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests((requests) -> requests
                        .requestMatchers("/design", "/orders").hasRole("USER")
                        .requestMatchers("/", "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN"))
                .csrf( (requests) -> requests
                    .ignoringRequestMatchers("/h2-console/**")
                )
                .headers( (requests) -> requests
                    .frameOptions().sameOrigin()
                )
          
                .formLogin((form) -> form
                        .loginPage("/login"))
                .logout((logout) -> logout.logoutSuccessUrl("/login"))
                .build();
        // .antMatchers().hasRole("USER")
        // .antMatchers("/", "/**").permitAll()
        // .and()
        // .build();

    }

}
