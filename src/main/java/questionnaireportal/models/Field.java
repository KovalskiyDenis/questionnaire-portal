package questionnaireportal.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="fields")
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String label;
    private Type type;
    private boolean isRequired;
    private boolean isActive;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private Set<String> options = new HashSet<>();

    public Field() {
    }

    public Field(String label, Type type, boolean isRequired, boolean isActive) {
        this.label = label;
        this.type = type;
        this.isRequired = isRequired;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isRequired() {
        return isRequired;
    }

    public void setRequired(boolean required) {
        isRequired = required;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<String> getOptions() {
        return options;
    }

    public void setOptions(Set<String> options) {
        this.options = options;
    }
}
