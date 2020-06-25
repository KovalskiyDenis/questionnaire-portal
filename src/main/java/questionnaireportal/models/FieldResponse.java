package questionnaireportal.models;

import javax.persistence.Embeddable;

@Embeddable
public class FieldResponse {

    private Long fieldId;
    private String fieldResponse;

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public String getFieldResponse() {
        return fieldResponse;
    }

    public void setFieldResponse(String fieldResponse) {
        this.fieldResponse = fieldResponse;
    }
}
