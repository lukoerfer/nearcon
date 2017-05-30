package de.inces.nearcon.overview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import de.inces.nearcon.R;
import de.inces.nearcon.conversations.Conversation;

public class ConversationAdapter extends ArrayAdapter<Conversation> {

    public ConversationAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Conversation conversation = this.getItem(position);
        if (view == null) {
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.item_conversation, parent, false);
        }
        ImageView imgIcon = (ImageView) view.findViewById(R.id.img_icon);
        TextView txtPreview = (TextView) view.findViewById(R.id.txt_preview);
        txtPreview.setText("Conversation Preview");
        return view;
    }
}
