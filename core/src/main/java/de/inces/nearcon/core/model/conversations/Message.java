package de.inces.nearcon.core.model.conversations;

import java.util.Calendar;
import de.inces.nearcon.core.model.users.User;
import lombok.Getter;

public class Message {

    @Getter
    private Calendar timestamp;
    @Getter
    private User sender;
    @Getter
    private String content;

    public Message (User sender, String content) {
        this.sender = sender;
        this.content = content;
    }

}
