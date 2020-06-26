package questionnaireportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import questionnaireportal.models.Response;
import questionnaireportal.repository.ResponseRepository;

@RestController
public class SocketController {

    @Autowired
    private ResponseRepository responseRepository;

    @MessageMapping("/saveResponse")
    @SendTo("/topic/activity")
    public ResponseEntity saveResponse(Response response) {
        return ResponseEntity.ok(responseRepository.save(response));
    }
}
