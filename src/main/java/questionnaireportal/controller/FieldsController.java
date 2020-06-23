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
@RequestMapping("/fieldsPage")
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

    @PostMapping
    public ResponseEntity create(@RequestBody Field fieldRequest) {
        System.out.println(fieldRequest.getLabel());
        System.out.println(fieldRequest.getOptions());
        System.out.println(fieldRequest.getType());
        System.out.println(fieldRequest.getIsActive());
        System.out.println(fieldRequest.getIsRequired());

        return ResponseEntity.ok(fieldsRepository.save(fieldRequest));
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Field field) {
        Optional<Field> optionalField = fieldsRepository.findById(field.getId());
        if(optionalField.isPresent()) {
            Field fieldFromDb = optionalField.get();
            BeanUtils.copyProperties(field, fieldFromDb, "id");
            return ResponseEntity.ok(fieldsRepository.save(fieldFromDb));
        }
        return ResponseEntity.ok("Not ok");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeField(@PathVariable("id") Long id) {
        Optional<Field> optionalField = fieldsRepository.findById(id);
        if(optionalField.isPresent()) {
            Field fieldFromDb = optionalField.get();
            fieldsRepository.delete(fieldFromDb);
            return ResponseEntity.ok("Ok");
        }
        return ResponseEntity.ok("Not ok");
    }
}
