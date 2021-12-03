package com.openlab.controller;


import com.openlab.pojo.User;
import com.openlab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String getUserList(Model model){
        List<User> userList = userService.getUserList();
        model.addAttribute("page",userList);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/userAdd";
    }

    @PostMapping("/add")
    public String createUser(User user){
        userService.creatUser(user);
        return "redirect:/manager/";
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(@PathVariable("id") Long id,Model model){
        User user= userService.getUser(id);
        model.addAttribute("user",user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user){
        userService.updateUser(user.getId(),user);
        return "redirect:/manager/";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/manager/";
    }
}
