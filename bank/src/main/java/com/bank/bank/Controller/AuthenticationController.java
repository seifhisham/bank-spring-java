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
    public ModelAndView getPost(){
        ModelAndView mav = new ModelAndView("Authentication.html");
       
        List<User> userList = userRepo.findAll();
        mav.addObject("users", userList);
        return mav;
    }
    @GetMapping("add-post")
    public ModelAndView getAddPostForm(){
        ModelAndView mav = new ModelAndView("AddUser.html");
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @PostMapping("save-post")
    public String savePost(@ModelAttribute User user){
        this.userRepo.save(user);
        return "redirect:/thymeleaf/View-User";
    }

    @GetMapping("delete-post")
    public String deletePost(@RequestParam("Id") String id){
        this.userRepo.deleteById(id);        
        return "redirect:/thymeleaf/View-User";
    }

    @GetMapping("update-post")
    public ModelAndView getUpdatePostForm(@RequestParam String Id){
        ModelAndView mav = new ModelAndView("AddUser.html");
        User olduser = this.userRepo.findById((String)Id).orElse(null);
        mav.addObject("user", olduser);
        return mav;
    }
    
}

