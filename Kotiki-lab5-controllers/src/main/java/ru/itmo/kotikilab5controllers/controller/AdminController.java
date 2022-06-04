package ru.itmo.kotikilab5controllers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itmo.kotikilab5controllers.kafkaCommunication.Message;

@Controller
public class AdminController {
    @Autowired
    private Message message;


//    @GetMapping("/admin")
//    public String userList(Model model) {
//        model.addAttribute("allUsers", message.sendMessage("fd",));
//        return "admin";
//    }

//    @PostMapping("/admin")
//    public String  deleteUser(@RequestParam(required = true, defaultValue = "" ) int userId,
//                              @RequestParam(required = true, defaultValue = "" ) String action,
//                              Model model) {
//        if (action.equals("delete")){
//            userService.deleteUser(userId);
//        }
//        return "redirect:/admin";
//    }
}
