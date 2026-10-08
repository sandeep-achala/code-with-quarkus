package org.user;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import io.quarkus.elytron.security.common.BcryptUtil;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String name;
    public String email;
    public String phoneNumber;
    public String password;

    public void setPassword(String password) {
        this.password = BcryptUtil.bcryptHash(password);
    }

    public boolean checkPassword(String password) {
        return BcryptUtil.matches(password, this.password);
    }
}
