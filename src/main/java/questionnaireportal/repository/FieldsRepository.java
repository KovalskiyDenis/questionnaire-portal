package questionnaireportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import questionnaireportal.models.Field;

import java.util.Optional;

public interface FieldsRepository extends JpaRepository<Field, Long> {
}
