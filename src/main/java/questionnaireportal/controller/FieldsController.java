package questionnaireportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import questionnaireportal.models.Field;
import questionnaireportal.repository.FieldsRepository;
import questionnaireportal.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        //System.out.println(user.getId());

        List<Field> fields = fieldsRepository.findAll();

        Map<Object, Object> response = new HashMap<>();
        response.put("fields", fields);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/fields")
    public ResponseEntity test (@RequestBody String body) {
        System.out.println("Fields working");
        System.out.println(body);
        return ResponseEntity.ok("Ok");
    }
}
