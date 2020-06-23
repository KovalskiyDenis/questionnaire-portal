package questionnaireportal.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import questionnaireportal.dto.response.UserResponseDto;
import questionnaireportal.models.User;
import questionnaireportal.repository.UserRepository;
import questionnaireportal.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AccountController {

    private final UserService userService;
    private final UserRepository userRepository;

    public AccountController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/login")
    public ResponseEntity getUser(@AuthenticationPrincipal User user) {

        UserResponseDto userResponse = new UserResponseDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber()
        );

        Map<Object, Object> response = new HashMap<>();
        response.put("user", userResponse);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/registration")
    public ResponseEntity registry(@RequestBody User user) {

        System.out.println(user.getEmail());
        System.out.println(user.getFirstName());
        System.out.println(user.getLastName());
        System.out.println(user.getPassword());
        System.out.println(user.getPhoneNumber());

        userService.register(user);

        return ResponseEntity.ok("User registered");
    }
}
