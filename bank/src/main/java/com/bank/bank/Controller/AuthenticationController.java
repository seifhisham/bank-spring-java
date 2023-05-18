package com.bank.bank.Controller;

import java.util.List;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bank.bank.Models.User;
import com.bank.bank.Repositories.UserRepo;
import com.bank.bank.Services.AuthService;

@Controller
@RequestMapping("/thymeleaf")
public class AuthenticationController {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("View-User")
    public ModelAndView getPost() {
        ModelAndView mav = new ModelAndView("Authentication.html");

        List<User> userList = userRepo.findAll();
        mav.addObject("users", userList);
        return mav;
    }

    @GetMapping("add-post")
    public ModelAndView getAddPostForm() {
        ModelAndView mav = new ModelAndView("AddUser.html");
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }


    @PostMapping("save-post")
    public String savePost(@ModelAttribute User user) {

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        user.setRole("USER");

        this.userRepo.save(user);

        return "redirect:/thymeleaf/View-User";

    }

    @PostMapping("save-role")
    public String saveRole(@ModelAttribute User user) {

        this.userRepo.save(user);

        return "redirect:/thymeleaf/View-User";

    }

    @GetMapping("delete-post")
    public String deletePost(@RequestParam("Id") String id) {
        this.userRepo.deleteById(id);
        return "redirect:/thymeleaf/View-User";
    }

    @GetMapping("update-post")
    public ModelAndView getUpdatePostForm(@RequestParam String Id) {
        ModelAndView mav = new ModelAndView("AddUser.html");
        User olduser = this.userRepo.findById((String) Id).orElse(null);
        mav.addObject("user", olduser);
        return mav;
    }

    @GetMapping("update-role")
    public ModelAndView getUpdaterole(@RequestParam String Id) {
        ModelAndView mav = new ModelAndView("ChangeRole.html");
        User olduser = this.userRepo.findById((String) Id).orElse(null);
        mav.addObject("user", olduser);
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView getLoginForm() {

        ModelAndView mav = new ModelAndView("login.html");
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("/login/save")
    public String login(@ModelAttribute User user) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities());

        Authentication authenticated = this.authService.authenticate(authentication);

        SecurityContextHolder.getContext().setAuthentication(authenticated);

        return "redirect:/thymeleaf/save-post";
    }

}
