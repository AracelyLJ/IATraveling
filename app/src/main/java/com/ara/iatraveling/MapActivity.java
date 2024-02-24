package com.ara.iatraveling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    List<Float> coordenates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        coordenates = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        String str_coordenates = "0,0";
        if (extras != null) {
            str_coordenates = extras.getString("coordenates");
        }

        String[] list_str_coordenates = str_coordenates.split(",");
        for (String s: list_str_coordenates) {
            coordenates.add(Float.valueOf(s));
        }
        Toast.makeText(this, coordenates.toString(), Toast.LENGTH_SHORT).show();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        LatLng location = new LatLng(coordenates.get(0), coordenates.get(1));
        googleMap.addMarker(new MarkerOptions().position(location).title("PAIS"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,12));
    }
}