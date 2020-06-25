package questionnaireportal.models;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.*;

@Entity
@Table(name = "responses")
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    /*@CollectionTable(name = "field_response", joinColumns = @JoinColumn(name = "response_id"))
    private HashMap<Long, String> fieldResponse = new HashMap<>();*/

    @ElementCollection(targetClass = FieldResponse.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "field_response", joinColumns = @JoinColumn(name = "response_id"))
    private List<FieldResponse> response = new ArrayList<>();

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public List<FieldResponse> getResponse() {
        return response;
    }

    public void setResponse(List<FieldResponse> response) {
        this.response = response;
    }

    /* public HashMap<Long, String> getFieldResponse() {
        return fieldResponse;
    }

    public void setFieldResponse(HashMap<Long, String> fieldResponse) {
        this.fieldResponse = fieldResponse;
    }*/
}
