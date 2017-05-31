package de.inces.nearcon.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.inces.nearcon.conversations.Conversation;
import de.inces.nearcon.conversations.Message;
import de.inces.nearcon.data.EventIconProvider;
import de.inces.nearcon.events.Event;
import de.inces.nearcon.events.EventIcon;
import de.inces.nearcon.events.EventLocation;
import de.inces.nearcon.users.User;

public class DataService extends Service {

    public static final String MAP_SERVICE = "MAP";
    public static final String CREATE_EVENT_SERVICE = "CREATE_EVENT";
    public static final String OVERVIEW_SERVICE = "OVERVIEW";
    public static final String CONVERSATION_SERVICE = "CONVERSATION";

    private User currentUser = new User("test1");
    private List<Event> mapEvents = new ArrayList<>();
    private List<Event> ownEvents = new ArrayList<>();
    private List<Message> messages = new ArrayList<>();

    @Override
    public void onCreate() {
        // Build map events
        mapEvents.add(new Event(new User("random1"), new EventIcon(0, "soccer"),
            "Wer möchte um 16:30 Uhr ein wenig im Soccer-Cage kicken?", new EventLocation(50.778598, 6.070815, 0.0)));
        mapEvents.add(new Event(new User("random2"), new EventIcon(0, "running"),
            "Joggen im Westpark - wer schließt sich an?", new EventLocation(50.771751, 6.068273, 1.0)));
        mapEvents.add(new Event(new User("random3"), new EventIcon(0, "bike"),
            "Fahrradtour nach Vaals mit Start am Audimax", new EventLocation(50.780294, 6.076051, 0.0)));
        Event movieEvent = new Event(new User("user1"), new EventIcon(0, "movie"),
            "Wer kommt mit zur Premiere des neues Fluch der Karibik?", new EventLocation(50.771775, 6.086595, 0.0));
        mapEvents.add(movieEvent);
        // Build own events
        ownEvents.add(movieEvent);
        // Build messages
        messages.add(new Message(new User("test1"), "Hi, kann ich mich bei euch noch anschließen?"));
        messages.add(new Message(new User("random2"), "Klar, komm einfach dazu!"));
        messages.add(new Message(new User("test1"), "Super, ich freu mich schon. Bis später!"));
    }

    public class MapBinder extends Binder {

        public List<Event> searchEvents() {
            return mapEvents;
        }

        public Conversation startConversation(Event event) {
            return new Conversation();
        }

    }

    public class CreateEventBinder extends Binder {

        public User getUser() {
            return DataService.this.currentUser;
        }

        public void createEvent(Event event) {
            Toast.makeText(DataService.this, "Successfully created event!", Toast.LENGTH_SHORT).show();
        }

    }

    public class OverviewBinder extends Binder {

        public User getUser() {
            return DataService.this.currentUser;
        }

        public List<Event> getOwnEvents() {
            return ownEvents;
        }

        public List<Conversation> getActiveConversations() {
            List<Conversation> conversations = new ArrayList<Conversation>();
            conversations.add(new Conversation());
            return conversations;
        }
    }

    public class ConversationBinder extends Binder {

        public User getUser() {
            return DataService.this.currentUser;
        }

        public List<Message> getMessages(Conversation conversation) {
            return messages;
        }

        public void sendMessage(Message message) {
            messages.add(message);
        }

    }

    private MapBinder mapBinder = new MapBinder();
    private CreateEventBinder createEventBinder = new CreateEventBinder();
    private OverviewBinder overviewBinder = new OverviewBinder();
    private ConversationBinder conversationBinder = new ConversationBinder();

    @Override
    public IBinder onBind(Intent intent) {
        switch (intent.getAction()) {
            case MAP_SERVICE:
                return this.mapBinder;
            case CREATE_EVENT_SERVICE:
                return this.createEventBinder;
            case OVERVIEW_SERVICE:
                return this.overviewBinder;
            case CONVERSATION_SERVICE:
                return this.conversationBinder;
            default:
                return null;
        }
    }
}
