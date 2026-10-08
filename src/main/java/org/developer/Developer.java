package org.developer;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name = "developer")
public class Developer extends PanacheEntity {
    // Public fields are automatically encapsulated with getters/setters by Panache at compile time
    public String name;
    public String programmingLanguage;
}
