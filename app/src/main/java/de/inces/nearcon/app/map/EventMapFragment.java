package de.inces.nearcon.app.map;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.inces.nearcon.R;
import de.inces.nearcon.app.conversation.ConversationActivity;
import de.inces.nearcon.core.model.events.Event;
import de.inces.nearcon.app.util.DynamicResources;

public class EventMapFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private DynamicResources dynRes;
    private GoogleMap map;
    Map<Marker,Event> markerEventDictionary;

    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View mContents;

        CustomInfoWindowAdapter() {

            mContents = getActivity().getLayoutInflater().inflate(R.layout.info_window, null);
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

        @Override
        public View getInfoContents(Marker marker) {
            render(marker, mContents);
            return mContents;
        }

        private void render(Marker marker, View view) {
            String snippet = marker.getSnippet();
            TextView snippetUi = ((TextView) view.findViewById(R.id.txt_snippet));
            snippetUi.setText(snippet);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        markerEventDictionary = new HashMap<Marker, Event>();
        this.getMapAsync(this);
        this.dynRes = new DynamicResources(getContext());
        return super.onCreateView(inflater,group,bundle);
    }


    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        map.setOnInfoWindowClickListener(this);
    }
    
    public void updateMap(List<Event> eventList){
        map.clear();
        for (Event event : eventList) {
            addEventMarker(event);
        }
    }

    private BitmapDescriptor bitmapFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private BitmapDescriptor getIconFromName(String name){
        Drawable drawable = getResources().getDrawable(dynRes.findDrawableByName(name));
        LayerDrawable layerDrawable = (LayerDrawable) getResources().getDrawable(R.drawable.marker_icon);
        layerDrawable.setDrawableByLayerId(R.id.drawable_icon, drawable);
        return bitmapFromDrawable(layerDrawable);
    }

    public void addEventMarker(Event event){
        LatLng position = new LatLng(event.getLocation().getLatitude(), event.getLocation().getLongitude());
        Marker marker = map.addMarker(new MarkerOptions().icon(getIconFromName(event.getIcon().getId())).position(position).snippet(event.getDescription()));
        if (event.getLocation().getRadius() > 0){
            map.addCircle(new CircleOptions()
                    .center(position)
                    .radius(event.getLocation().getRadius())
                    .strokeColor(0x00ffffff)
                    .fillColor(0x33333333));
        }
        markerEventDictionary.put(marker,event);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(position,13.0f));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        //Toast.makeText(this.getActivity(), markerEventDictionary.get(marker).getDescription(),
         //       Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), ConversationActivity.class);
        startActivity(intent);
    }
}