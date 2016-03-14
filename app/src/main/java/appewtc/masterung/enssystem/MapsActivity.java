package appewtc.masterung.enssystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final double latCenterDOUBLE = 13.66752591;
    private static final double lngCenterDOUBLE = 100.62174082;
    private double latMarkerADouble, lngMarkerADouble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);
    }   // Main Method

    public void clickSentLocation(View view) {
        Intent intent = new Intent(MapsActivity.this, InformActivity.class);

        String strName = getIntent().getStringExtra("nameLogin");
        intent.putExtra("nameLogin", strName);

        intent.putExtra("douLat", latMarkerADouble);
        intent.putExtra("douLng", lngMarkerADouble);
        startActivity(intent);
        finish();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Move to My Center
        LatLng centerLatLng = new LatLng(latCenterDOUBLE, lngCenterDOUBLE);
        mMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(centerLatLng, 17));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                mMap.clear();

                latMarkerADouble = latLng.latitude;
                lngMarkerADouble = latLng.longitude;

                LatLng markerLatLng1 = new LatLng(latMarkerADouble, lngMarkerADouble);

                mMap.addMarker(new MarkerOptions()
                        .position(markerLatLng1)
                .title("เหตุเกิดที่นี่ ?")
                .snippet(Double.toString(latMarkerADouble) + ", " + Double.toString(lngMarkerADouble)));

            }   // event
        });


    }   // Second Method
}   // Main Class
