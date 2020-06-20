package questionnaireportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import questionnaireportal.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByFirstName(String firstName);
}
