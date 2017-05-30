package de.inces.nearcon.map;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import de.inces.nearcon.R;
import de.inces.nearcon.events.Event;

public class EventMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle bundle){
        this.getMapAsync(this);
        return super.onCreateView(inflater,group,bundle);
    }

    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        updateMap();
    }
    
    public void updateMap(){
            LatLng position = new LatLng(50.775346, 6.083887);
            map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_add_white_24dp)).position(position));
            map.moveCamera(CameraUpdateFactory.newLatLng(position));
        }
}