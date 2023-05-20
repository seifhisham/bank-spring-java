package com.bank.bank.Controller;

import java.util.List;

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

    @GetMapping("Add-User")
    public ModelAndView getAddPostForm() {
        ModelAndView mav = new ModelAndView("AddUser.html");
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("Save-User")
    public String savePost(@ModelAttribute User user) {

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        user.setRole("USER");

        this.userRepo.save(user);

        return "redirect:/thymeleaf/View-User";

    }

    @PostMapping("Save-Role")
    public String saveRole(@ModelAttribute User user) {

        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        this.userRepo.save(user);

        return "redirect:/thymeleaf/View-User";

    }

    @GetMapping("Delete-User")
    public String deletePost(@RequestParam("Id") String id) {
        this.userRepo.deleteById(id);
        return "redirect:/thymeleaf/View-User";
    }

    @GetMapping("Update-User")
    public ModelAndView getUpdatePostForm(@RequestParam String Id) {
        ModelAndView mav = new ModelAndView("AddUser.html");
        User olduser = this.userRepo.findById((String) Id).orElse(null);
        mav.addObject("user", olduser);
        return mav;
    }

    @GetMapping("Update-Role")
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

        return "redirect:/thymeleaf/Save-User";
    }
}
