package com.hieuhd.tracklocation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    FirebaseDatabase data;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        data = FirebaseDatabase.getInstance();
         myRef = data.getReference("Location");


    }
    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ViTri vt = dataSnapshot.getValue(ViTri.class);
                Toast.makeText(MapsActivity.this, ""+ vt.lati, Toast.LENGTH_SHORT).show();

                LatLng location = new LatLng(vt.lati,vt.longti);
               mMap.addMarker(new MarkerOptions().position(location).title("Device Location"));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,10));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
