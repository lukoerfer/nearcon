package de.inces.nearcon.event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import de.inces.nearcon.R;
import de.inces.nearcon.events.EventIcon;
import de.inces.nearcon.util.DynamicResources;

public class IconAdapter extends ArrayAdapter<EventIcon> {

    private DynamicResources dynamicResources;

    public IconAdapter(Context context) {
        super(context, 0);
        this.dynamicResources = new DynamicResources(this.getContext());
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        EventIcon icon = this.getItem(position);
        if (view == null) {
            view = LayoutInflater.from(this.getContext()).inflate(R.layout.item_icon, parent, false);
        }
        ((ImageView) view).setImageResource(dynamicResources.findDrawableByName(icon.getId()));
        return view;
    }
}
