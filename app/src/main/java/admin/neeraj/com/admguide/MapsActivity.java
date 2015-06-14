package admin.neeraj.com.admguide;


import android.app.FragmentManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity{

    private GoogleMap mMap;
    private static CameraUpdateFactory cf;
    LatLng dean = new LatLng(25.492380,81.862879);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {

        if(mMap == null)
        {
            mMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        }
        if(mMap!=null)
        {
            setupMap();
        }
    }

    private void setupMap() {
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        Marker Dean = mMap.addMarker(new MarkerOptions().position(dean).title("Dean, Academic").snippet("MNNIT Allahabad Campus, Teliarganj\n" +
                "Allahabad, Uttar Pradesh 211002"));
        CameraUpdate cameraUpdate =cf.newLatLngZoom(dean, 15);
        mMap.animateCamera(cameraUpdate);
    }
}