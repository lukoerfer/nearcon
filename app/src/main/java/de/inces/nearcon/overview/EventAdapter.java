package de.inces.nearcon.overview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import de.inces.nearby.events.Event;

public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(Context context) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        return view;
    }
}
