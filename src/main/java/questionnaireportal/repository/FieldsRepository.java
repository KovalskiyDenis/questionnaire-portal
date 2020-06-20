package questionnaireportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import questionnaireportal.models.Field;

public interface FieldsRepository extends JpaRepository<Field, Long> {
}
