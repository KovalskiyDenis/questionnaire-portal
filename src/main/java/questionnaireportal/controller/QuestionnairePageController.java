package questionnaireportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import questionnaireportal.models.Response;
import questionnaireportal.repository.FieldsRepository;
import questionnaireportal.repository.ResponseRepository;

@RestController
@RequestMapping("/questionnaire")
public class QuestionnairePageController {

    @Autowired
    private FieldsRepository fieldsRepository;

    @Autowired
    private ResponseRepository responseRepository;

    @GetMapping
    public ResponseEntity getFields() {
        return ResponseEntity.ok(fieldsRepository.findAll());
    }

    @PostMapping
    public ResponseEntity saveResponse(@RequestBody Response response) {
        return ResponseEntity.ok(responseRepository.save(response));
    }
}
