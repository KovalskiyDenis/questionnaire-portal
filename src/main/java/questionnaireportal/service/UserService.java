package questionnaireportal.service;

import questionnaireportal.models.User;

import java.util.List;

public interface UserService {

    User register(User user);

    User findByUsername(String username);
}


