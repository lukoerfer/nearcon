package de.inces.nearby.conversations;

import de.inces.nearby.events.Event;
import de.inces.nearby.users.User;

public class Conversation {

    private Event Topic;
    private User Requestor;

    public Event getTopic() {
        return this.Topic;
    }

    public User getRequestor() {
        return this.Requestor;
    }
}
