package com.bank.bank.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.bank.bank.Services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    @Bean

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Configuring the HTTP security

        http.userDetailsService(userService) // Using the user service for user details

                .authorizeRequests()

                 .antMatchers("/thymeleaf/View-User").hasAuthority("ADMIN") 

                // ADMIN authority

                .antMatchers("/thymeleaf/add-post", "/thymeleaf/save-post").permitAll()

                .anyRequest().authenticated() // All other requests require authentication

                .and()

                .formLogin() // Configuring form-based login

                .loginPage("/thymeleaf/login") // Specifying the login page URL

                .loginProcessingUrl("/thymeleaf/login/save") // Specifying the URL for processing the login form

                .defaultSuccessUrl("/thymeleaf/View-User") // Specifying the default URL to redirect after successful

                // login

                .permitAll() // Allowing access to the login page for all users

                .and()

                .logout() // Configuring logout

                .permitAll() // Allowing access to the logout URL for all users

                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout")) // Specifying the logout URL

                .logoutSuccessUrl("/thymeleaf/login"); // Specifying the URL to redirect after successful logout

        return http.build(); // Building and returning the security filter chain

    }
}
