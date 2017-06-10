package de.inces.nearcon.app.conversation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import de.inces.nearcon.core.model.conversations.Message;
import de.inces.nearcon.R;
import de.inces.nearcon.core.model.users.User;

public class MessageAdapter extends ArrayAdapter<Message> {

    private User user;

    public MessageAdapter(Context context, User user) {
        super(context, 0);
        this.user = user;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Message message = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.item_message, parent, false);
        }
        TextView txtContent = (TextView) view.findViewById(R.id.txt_content);
        txtContent.setText(message.getContent());
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) txtContent.getLayoutParams();
        if (message.getSender().getId().equals(this.user.getId())) {
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.rightMargin = 15;
            params.leftMargin = 80;
            txtContent.setBackgroundResource(R.drawable.item_message_background_sent);
        } else {
            params.removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.leftMargin = 15;
            params.rightMargin = 80;
            txtContent.setBackgroundResource(R.drawable.item_message_background_received);
        }
        txtContent.setLayoutParams(params);
        return view;
    }
}
