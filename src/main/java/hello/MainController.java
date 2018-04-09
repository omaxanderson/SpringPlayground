package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import hello.User;
import hello.UserRepository;

@Controller
@RequestMapping
public class MainController {
    @Autowired
    public UserRepository userRepository;

    @GetMapping("/add")
    public @ResponseBody String newUser(@RequestParam String name,
                                        @RequestParam String email, @RequestParam String password) {
        // ResponseBody indicates we are returning the actual string value, not a view
        User u = new User();
        u.setUsername(name);
        u.setPassword(password);
        u.setEmail(email);
        userRepository.save(u);
        return "new user successfully added";
    }

    @GetMapping("/user")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public @ResponseBody Iterable<User> getUserById(@PathVariable Integer id) {
        return userRepository.findAllById(id);
    }
}
