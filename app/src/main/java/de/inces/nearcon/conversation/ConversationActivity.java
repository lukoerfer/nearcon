package de.inces.nearcon.conversation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import de.inces.nearby.conversations.Message;
import de.inces.nearcon.R;

public class ConversationActivity extends AppCompatActivity {

    private MessageAdapter Messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_conversation);
        this.initializeMessageAdapter();
    }

    private void initializeMessageAdapter() {
        this.Messages = new MessageAdapter(this);
        ((ListView) this.findViewById(R.id.list_messages)).setAdapter(this.Messages);
    }




}
