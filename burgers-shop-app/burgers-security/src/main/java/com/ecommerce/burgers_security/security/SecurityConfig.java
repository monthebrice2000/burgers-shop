package com.ecommerce.burgers_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.ecommerce.burgers_models.models.User;
import com.ecommerce.burgers_repository.repository.UserRepository;

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
    @Order(3)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeRequests((requests) -> requests
                        .requestMatchers("/design", "/orders").hasRole("USER")
                        .requestMatchers("/", "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.POST, "/api/**").hasRole("USER")
                        // .requestMatchers("/api/ingredients/**").hasRole("ADMIN")
                        // .requestMatchers(HttpMethod.GET, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                        // .requestMatchers(HttpMethod.POST, "/api/ingredients").hasAuthority("SCOPE_writeIngredients")
                        // .requestMatchers(HttpMethod.DELETE, "/api/ingredients").hasAuthority("SCOPE_deleteIngredients")
                        // .requestMatchers(EndpointRequest.to("beans", "threaddump", "loggers")).hasRole("ADMIN")
                        .anyRequest().authenticated()
                        )
                // .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .csrf((requests) -> requests
                        .ignoringRequestMatchers("/h2-console/**")
                        // .ignoringRequestMatchers("/api/**")
                        )
                // .csrf().disable()
                .headers((requests) -> requests
                        .frameOptions().sameOrigin())
                // .formLogin()
                // .and()
                .formLogin((form) -> form
                        .loginPage("/login").defaultSuccessUrl("/home"))
                .logout((logout) -> logout.logoutSuccessUrl("/login"))
                .build();
    }

}
