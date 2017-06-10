package de.inces.nearcon.core.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;

@Entity
public class User {

    @Id
    @GeneratedValue
    @Getter
    private long id;

    @Column
    @Getter
    private long reputation;

}
