package com.example.catalyst.trimetmapper.activities;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.catalyst.trimetmapper.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private final String TAG = MainActivity.class.getSimpleName();

    private GoogleMap mMap;

   // @Bind(R.id.focus_layout) LinearLayout focus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // ButterKnife.bind(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        LatLngBounds bounds = new LatLngBounds(new LatLng(44.6368, -124), new LatLng(46.9754, -121));
        autocompleteFragment.setBoundsBias(bounds);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.i(TAG, "Place: " + place.getName());
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "An error occurred: " + status);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // initialize the map to the Portland area
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.5231, -122.6765), 9.5f));

        // disable zooming out beyond the Portland area
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition position) {
                float minZoom = 8.0f;

                if (position.zoom < minZoom) {
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(minZoom));
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();

        //refocuses the invisible element.
      //  focus.requestFocus();
        // register EventBus
       // EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        // unregister EventBus
        //EventBus.getDefault().unregister(this);

        super.onPause();
    }


}
