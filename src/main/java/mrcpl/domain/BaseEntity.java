package mrcpl.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@MappedSuperclass
public class BaseEntity<T extends Serializable> {

    @Id
    @GeneratedValue(generator = "seq-generator")
    @GenericGenerator(name = "seq-generator", strategy = "mrcpl.configuration.EntityIdGenerator")
    @Column(updatable = false)
    @Setter(AccessLevel.PROTECTED)
    @Getter
    private T id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "(id=" + id + ')';
    }
}