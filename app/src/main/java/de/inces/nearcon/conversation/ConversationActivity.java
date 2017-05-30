package de.inces.nearcon.conversation;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import de.inces.nearcon.conversations.Message;
import de.inces.nearcon.R;

public class ConversationActivity extends AppCompatActivity {

    private MessageAdapter Messages;
    protected Button btnSend;
    protected EditText editMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_conversation);
        this.initializeMessageAdapter();

        this.editMessage = (EditText) findViewById(R.id.edit_message);

        this.initSendButton();
    }

    private void initializeMessageAdapter() {
        this.Messages = new MessageAdapter(this);
        ((ListView) this.findViewById(R.id.list_messages)).setAdapter(this.Messages);
    }


    private void initSendButton(){
        this.btnSend = (Button) findViewById(R.id.btn_send);

        this.btnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // What to do when submitting
                // Or show warning if you have to do something more

                //first get descriptiontext
                String message = editMessage.getText().toString();
                //TODO make an if condition for "no test added"
                Messages.add(new Message(message));
                editMessage.setText("");


            }
        });
    }

}
