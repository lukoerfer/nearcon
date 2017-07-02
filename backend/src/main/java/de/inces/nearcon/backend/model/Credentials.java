package de.inces.nearcon.backend.model;

import lombok.Getter;
import lombok.Setter;

public class Credentials {

    @Getter @Setter
    private String username;

    @Getter @Setter
    private String password;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
