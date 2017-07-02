package de.inces.nearcon.backend.services.conversations;

import de.inces.nearcon.backend.Components;
import de.inces.nearcon.backend.persistence.PersistenceManager;

public class ConversationService {

    private PersistenceManager persistenceManager;

    public ConversationService() {
        this.persistenceManager = Components.get(PersistenceManager.class);
    }


}
