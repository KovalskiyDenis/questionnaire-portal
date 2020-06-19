package questionnaireportal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import questionnaireportal.dto.request.AuthenticationRequestDto;
import questionnaireportal.models.User;
import questionnaireportal.repository.UserRepository;
import questionnaireportal.security.jwt.JwtTokenProvider;
import questionnaireportal.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AccountController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider  jwtTokenProvider;

    public AccountController(UserRepository userRepository, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestUser) {
        try {
            String username = requestUser.getEmail();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestUser.getPassword()));

            User user = userService.findByUsername(username);

            if(user == null) {
                throw new UsernameNotFoundException("User with " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username);

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid credentials");
        }
    }


    @PostMapping("/registry")
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
