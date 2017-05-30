package de.inces.nearcon.map;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
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
import com.google.android.gms.maps.model.BitmapDescriptor;
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
import de.inces.nearcon.events.EventIcon;
import de.inces.nearcon.events.EventLocation;
import de.inces.nearcon.util.DynamicResources;

public class EventMapFragment extends SupportMapFragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private DynamicResources dynRes;
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
        this.dynRes = new DynamicResources(getContext());
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
        markerEventDictionary.put(marker,event);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(position,13.0f));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this.getActivity(), markerEventDictionary.get(marker).getDescription(),
                Toast.LENGTH_SHORT).show();
    }
}