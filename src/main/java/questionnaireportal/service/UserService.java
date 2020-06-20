package questionnaireportal.service;

import questionnaireportal.models.User;

import java.util.List;

public interface UserService {

    User register(User user);

    User findByEmail(String email);

    User findByFirstName(String firstName);
}


