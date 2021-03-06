package de.inces.nearcon.app.overview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import de.inces.nearcon.R;
import de.inces.nearcon.core.model.conversations.Conversation;
import de.inces.nearcon.app.util.DynamicResources;

public class ConversationAdapter extends ArrayAdapter<Conversation> {

    private DynamicResources dynamicResources;

    public ConversationAdapter(Context context) {
        super(context, 0);
        this.dynamicResources = new DynamicResources(this.getContext());
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
        int iconId = this.dynamicResources.findDrawableByName("basketball");
        imgIcon.setImageResource(iconId);
        return view;
    }
}
