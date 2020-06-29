package questionnaireportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import questionnaireportal.models.User;
import questionnaireportal.repository.UserRepository;
import questionnaireportal.service.MailSender;
import questionnaireportal.service.UserService;

@Service

public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final MailSender mailSender;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, MailSender mailSender) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    public void send(String emailTo, String subject, String message) {
        mailSender.send(emailTo, subject, message);
    }

    @Override
    public boolean register(User user) {

        User userFromDB = userRepository.findByEmail(user.getEmail());
        if(userFromDB != null) {
            return false;
        }

        String message = String.format("Hello, %s!\n" +
                "You have successfully registered on the site: http://localhost:8080/", user.getFirstName());
        send(user.getEmail(), "Notification", message);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        return true;
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByFirstName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }
}
