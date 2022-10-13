package fintech.middle.jpa.v1.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public abstract class NamedEntity {
    private String name;
}
