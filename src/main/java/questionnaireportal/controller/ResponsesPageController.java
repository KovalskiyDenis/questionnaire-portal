package questionnaireportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import questionnaireportal.repository.FieldsRepository;
import questionnaireportal.repository.ResponseRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/responsesPage")
public class ResponsesPageController {

    @Autowired
    private FieldsRepository fieldsRepository;

    @Autowired
    private ResponseRepository responseRepository;

    @GetMapping
    public ResponseEntity getResponses() {

        Map<Object, Object> response = new HashMap<>();

        response.put("fields", fieldsRepository.findActive());
        response.put("responses", responseRepository.findAll());

        return ResponseEntity.ok(response);
    }
}
