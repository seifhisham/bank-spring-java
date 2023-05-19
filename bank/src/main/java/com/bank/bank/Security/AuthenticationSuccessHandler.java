package com.bank.bank.Security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        }

        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("EMPLOYEE"))) {
            // Redirect to admin home page
            setDefaultTargetUrl("/EmployeeHome.html");
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            // Redirect to admin home page
            setDefaultTargetUrl("/AdminDashboard.html");
        } else {
            // Redirect to customer home page
            setDefaultTargetUrl("/UserHome.html");
        }

        super.handle(request, response, authentication);
    }
}
