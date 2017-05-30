package de.inces.nearcon.overview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import de.inces.nearcon.R;
import de.inces.nearcon.conversations.Conversation;
import de.inces.nearcon.events.Event;

public class OverviewActivity extends AppCompatActivity {

    private EventAdapter Events;
    private ConversationAdapter Conversations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_overview);
        this.initializeEventAdapter();
        this.initializeConversationAdapter();

        for (int i = 0; i < 10; i++) {
            this.Conversations.add(new Conversation());
        }
        for (int i = 0; i < 10; i++) {
            this.Events.add(new Event());
        }
    }

    private void initializeEventAdapter() {
        this.Events = new EventAdapter(this);
        ((ListView) this.findViewById(R.id.list_events)).setAdapter(this.Events);
    }

    private void initializeConversationAdapter() {
        this.Conversations = new ConversationAdapter(this);
        ((ListView) this.findViewById(R.id.list_conversations)).setAdapter(this.Conversations);
    }
}
