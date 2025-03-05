package com.fadeevaaa.time_tracker.controller;

import com.fadeevaaa.time_tracker.models.User;
import com.fadeevaaa.time_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hello/{id}")
    public User hello(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @PostMapping()
    public String register(@ModelAttribute("user") User user, Model model) {
        String message = userService.saveUser(user);
        model.addAttribute("message", message);
        return "users/new_status";
    }

    @GetMapping("/:id")
    public String chooseUser() {
        return "users/get_user";
    }

    @PostMapping("/:id/new")
    public String login(@RequestParam("login") String login, @RequestParam("password") String password, Model model) {
        String message = userService.login(login, password);
        if (!message.isEmpty()) {
            model.addAttribute("message", message);
            return "users/new_status";
        }
        return "users/user_menu";
    }

    @GetMapping("/:id/edit")
    public String changeUserData(Model model) {
        model.addAttribute("user", userService.currentUser);
        return "users/edit_user";
    }

    @PostMapping("/:id/edit")
    public String saveChanges(@ModelAttribute("user") User user) {
        userService.editUser(user);
        return "users/user_menu";
    }

    @GetMapping("/:id/delete")
    public String deleteUser() {
        userService.deleteUser();
        return "main_menu";
    }
}
