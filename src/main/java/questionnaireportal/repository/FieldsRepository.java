package questionnaireportal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import questionnaireportal.models.Field;

import java.util.List;

public interface FieldsRepository extends JpaRepository<Field, Long> {

    @Query(value="select * from fields where is_active = 'True'", nativeQuery = true)
    List<Field> findActive();
}
