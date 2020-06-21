package questionnaireportal.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import questionnaireportal.dto.request.FieldRequestDto;
import questionnaireportal.models.Field;
import questionnaireportal.repository.FieldsRepository;
import questionnaireportal.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class FieldsController {

    private final FieldsRepository fieldsRepository;
    private final UserService userService;

    @Autowired
    public FieldsController(FieldsRepository fieldsRepository, UserService userService) {
        this.fieldsRepository = fieldsRepository;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getFields () {
        List<Field> fields = fieldsRepository.findAll();

        Map<Object, Object> response = new HashMap<>();
        response.put("fields", fields);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/fields")
    public ResponseEntity create(@RequestBody FieldRequestDto requestField) {
        System.out.println(requestField.isActive());
        System.out.println(requestField.isRequired());

        Field field = new Field(requestField.getLabel(), requestField.getType(), requestField.isRequired(), requestField.isActive());

        if(requestField.getOptions() != null) {
            for (String option : requestField.getOptions().split("\n")) {
                field.getOptions().add(option);
            }
        }
        return ResponseEntity.ok(fieldsRepository.save(field));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Field field) {
        Optional<Field> optionalField = fieldsRepository.findById(field.getId());
        if(optionalField.isPresent()) {
            Field fieldFromDb = optionalField.get();
            BeanUtils.copyProperties(field, fieldFromDb, "id");
            fieldsRepository.save(fieldFromDb);
            return ResponseEntity.ok("Ok");
        }
        return ResponseEntity.ok("Not ok");
    }
}
