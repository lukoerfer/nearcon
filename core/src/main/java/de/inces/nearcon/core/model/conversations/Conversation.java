package de.inces.nearcon.core.model.conversations;

import de.inces.nearcon.core.model.events.Event;
import de.inces.nearcon.core.model.users.User;
import lombok.Getter;

public class Conversation {

    @Getter
    private Event topic;
    @Getter
    private User requestor;

}
