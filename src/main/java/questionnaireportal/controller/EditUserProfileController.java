package questionnaireportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import questionnaireportal.dto.request.ChangePasswordDto;
import questionnaireportal.dto.request.EditProfileDto;
import questionnaireportal.models.User;
import questionnaireportal.repository.UserRepository;

import java.util.Optional;

@RestController
public class EditUserProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("/editProfile")
    public ResponseEntity updateProfile(@RequestBody EditProfileDto profile) {

        Optional<User> userFromDb = userRepository.findById(profile.getId());
        if(userFromDb.isPresent()) {
            User user = userFromDb.get();
            user.setPhoneNumber(profile.getPhoneNumber());
            user.setEmail(profile.getEmail());
            user.setFirstName(profile.getFirstName());
            user.setLastName(profile.getLastName());

            return ResponseEntity.ok(userRepository.save(user));
        }
        return ResponseEntity.ok("");
    }

    @PutMapping("/changePassword")
    public ResponseEntity changePassword(@RequestBody ChangePasswordDto newPasswordForm) {

        Optional<User> userFromDb = userRepository.findById(newPasswordForm.getId());

        if(userFromDb.isPresent()) {
            User user = userFromDb.get();
            if(passwordEncoder.matches(newPasswordForm.getPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(newPasswordForm.getNewPassword()));
                return ResponseEntity.ok(userRepository.save(user));
            }
        }
        return ResponseEntity.ok("Not OK");
    }
}
