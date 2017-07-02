package de.inces.nearcon.core.model.users;

import java.security.Principal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class User {

    @Id
    @GeneratedValue
    @Getter
    private long id;

    @Getter @Setter
    private long reputation;

    @Getter
    private String secret;

    @Override
    public String toString() {
        return User.class.getSimpleName() + "#" + Long.toString(id);
    }
}
