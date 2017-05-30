package de.inces.nearcon.map;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import de.inces.nearcon.R;
import de.inces.nearcon.events.Event;

public class EventMapFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    GoogleMap map;
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
        return super.onCreateView(inflater,group,bundle);
    }


    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setInfoWindowAdapter(new CustomInfoWindowAdapter());
        map.setOnInfoWindowClickListener(this);
        updateMap();
    }
    
    public void updateMap(){
        //addEventMarker(eve);
    }

    public void addEventMarker(Event event){
        LatLng position = new LatLng(event.getLocation().getLatitude(), event.getLocation().getLongitude());
        Marker marker = map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_add_white_24dp)).position(position).snippet(event.getDescription()));
        markerEventDictionary.put(marker,event);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(position,13.0f));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this.getActivity(), markerEventDictionary.get(marker).getDescription(),
            Toast.LENGTH_SHORT).show();
    }
}