package com.bank.bank.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthService implements AuthenticationProvider {
    @Autowired
    private UserService userService; // Autowiring the UserService bean

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; // Autowiring the BCryptPasswordEncoder bean

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String Email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails userDetails = this.userService.loadUserByUsername(Email);

        if (userDetails == null) {
            throw new UsernameNotFoundException("Invalid Email or password");

        }
        if (!this.bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
            throw new UsernameNotFoundException("Invalid Email or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(),
                userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);

    }

}
