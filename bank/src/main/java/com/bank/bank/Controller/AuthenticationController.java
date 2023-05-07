package com.bank.bank.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bank.bank.Models.User;
import com.bank.bank.Repositories.UserRepo;
@Controller
@RequestMapping("/thymeleaf")
public class AuthenticationController {

    @Autowired
    private UserRepo userRepo;
    @GetMapping("View-User")
    public ModelAndView getAddPostForm(){
        ModelAndView mav = new ModelAndView("Authentication.html");
       
        List<User> userList = userRepo.findAll();
        mav.addObject("users", userList);
        return mav;
    }
}
