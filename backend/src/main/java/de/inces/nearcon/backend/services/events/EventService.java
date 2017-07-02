package de.inces.nearcon.backend.services.events;

import de.inces.nearcon.backend.Components;
import de.inces.nearcon.backend.persistence.PersistenceManager;

public class EventService {

    private PersistenceManager persistenceManager;

    public EventService() {
        this.persistenceManager = Components.get(PersistenceManager.class);
    }



}
