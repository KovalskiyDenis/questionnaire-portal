package questionnaireportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import questionnaireportal.models.Response;

public interface ResponseRepository extends JpaRepository<Response, Long> {
}
