package de.inces.nearcon.conversation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import de.inces.nearby.conversations.Message;
import de.inces.nearcon.R;

public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Message message = getItem(position);
        if (view == null) {
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.item_message, parent, false);
        }
        TextView txtContent = (TextView) view.findViewById(R.id.txt_content);
        txtContent.setText(message.getContent());
        return view;
    }
}
