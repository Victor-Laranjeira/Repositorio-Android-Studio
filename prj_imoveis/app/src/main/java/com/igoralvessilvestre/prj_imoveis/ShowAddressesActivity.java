package com.igoralvessilvestre.prj_imoveis;

import androidx.fragment.app.FragmentActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class ShowAddressesActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EstatesData db;
    private List<Estate> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_addresses);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        db = new EstatesData(this);
        lista = db.inserirImoveis();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        LatLng marker = null;
        Bundle extras = getIntent().getExtras();

        if(extras == null){
            int tam = lista.size();
            Estate e;
            for(int i = 0; i < tam; i++){
                e = lista.get(i);
                marker = new LatLng(e.latitude, e.longitude);
                mMap.addMarker(new MarkerOptions().position(marker).title(getString(R.string.imov_contato)+e.PHONE));
            }
        }else{
            marker = new LatLng(extras.getDouble("latitude"), extras.getDouble("longitude"));
            mMap.addMarker(new MarkerOptions().position(marker).title(getString(R.string.imov_contato)+extras.getString("phone")));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));
    }
}