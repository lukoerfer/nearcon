package de.inces.nearcon.core.model.auth;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;

@Entity
public class Device {

    @Id
    @GeneratedValue
    @Getter
    private long id;

    @Getter
    private String secret;

    @ManyToOne
    private User user;

}
