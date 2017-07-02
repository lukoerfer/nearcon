package de.inces.nearcon.backend.services.internal;

import java.util.HashMap;
import java.util.Map;

import de.inces.nearcon.backend.model.Credentials;

public class AnonymousService {

    private Map<String, String> access;

    public AnonymousService() {
        this.access = new HashMap<>();
    }

}
