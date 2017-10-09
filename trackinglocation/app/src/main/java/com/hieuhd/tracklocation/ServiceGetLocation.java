package com.hieuhd.tracklocation;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by HieuHD on 9/8/2017.
 */

public class ServiceGetLocation extends Service {

    DatabaseReference data;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        data = FirebaseDatabase.getInstance().getReference();

        Toast.makeText(this, "Service is Running", Toast.LENGTH_SHORT).show();

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        String provider = locationManager.GPS_PROVIDER;

        locationManager.requestLocationUpdates(provider, 1000, 10, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double lati = location.getLatitude();
                double longti = location.getLongitude();
                Toast.makeText(ServiceGetLocation.this, "Lati"+lati+"LongTi"+longti, Toast.LENGTH_SHORT).show();
                ViTri vt = new ViTri(lati, longti);

                data.child("Location").setValue(vt);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        });
        return START_STICKY;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
