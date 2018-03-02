package de.inces.nearcon.core.model.auth;

import java.security.Principal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class User implements Principal {

    @Id
    @GeneratedValue
    @Getter
    private long id;

    @Column
    @Getter
    private String secret;

    @OneToMany
    @Getter
    private List<Device> devices;

    @Getter @Setter
    private long reputation;

    @Override
    public String getName() {
        return Long.toString(this.id);
    }
}
