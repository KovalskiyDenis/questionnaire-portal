package questionnaireportal.controller;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import questionnaireportal.models.User;
import questionnaireportal.repository.UserRepository;

@RestController
@RequestMapping("/accounting")
public class AccountController {

    private final UserRepository userRepository;

    public AccountController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/registry")
    public User registry(@RequestBody User user) {
        System.out.println(user.getEmail());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getPassword());
        System.out.println(user.getPhoneNumber());

        return userRepository.save(user);
    }
}
