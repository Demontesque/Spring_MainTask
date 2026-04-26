//package hiber.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping("/users")
//public class UserController {
//
//    @GetMapping
//    public String list() {
//        System.out.println("UserController.list() called!");
//        return "users";
//    }
//}
package hiber.controller;

import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Test method works!";
    }
    @GetMapping
    public String list(Model model) {
        List<User> user = userService.listUsers();
        model.addAttribute("users", user);
        return "users";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping
    public String newUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user, @PathVariable Long id) {
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/users";
    }

@PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return "redirect:/users";





}
}

////package hiber.controller;
////
////import org.springframework.stereotype.Controller;
////import org.springframework.web.bind.annotation.GetMapping;
////import org.springframework.web.bind.annotation.ResponseBody;
////
////@Controller
////public class UserController {
////
////    @GetMapping("/")
////    @ResponseBody
////    public String root() {
////        return "Application is running!";
////    }
////
//    @GetMapping("/test")
//    @ResponseBody
//    public String test() {
//        return "Test OK";
//    }
//}
//}