package com.igoralvessilvestre.prj_imoveis;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.INTERNET;

public class Coordenadas{
    public double latitude;
    public double longitude;


/*
    @SuppressLint("MissingPermission")
    public void addContext(Context context){

         //   Log.v("aqui", "entrou");

        Log.v("aqui", "opa");
        locMng = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        provider = locMng.getBestProvider(criteria, false);
        Log.v("mano", "oi");
        Location location = locMng.getLastKnownLocation(provider);

        if (location != null){
            Log.v("mano", "Provider "+ provider+" foi selecionado");
            onLocationChanged(location);
        }
    }

    private boolean checkPermission(Context context){
        int internetResult = ContextCompat.checkSelfPermission(context.getApplicationContext(), INTERNET);
        int fineLocationResult = ContextCompat.checkSelfPermission(context.getApplicationContext(), ACCESS_FINE_LOCATION);
        int coarseLocationResult = ContextCompat.checkSelfPermission(context.getApplicationContext(), ACCESS_COARSE_LOCATION);
        Log.v("perm", ""+internetResult+" "+fineLocationResult+" "+coarseLocationResult);

        return internetResult == PackageManager.PERMISSION_GRANTED &&
                fineLocationResult == PackageManager.PERMISSION_GRANTED &&
                coarseLocationResult == PackageManager.PERMISSION_GRANTED;
    }

    private  void requestPermission(Context context){
        ActivityCompat.requestPermissions(context., PERMISSIONS, PERMISSION_REQUEST_CODE );
        context.recreate();  //recria a activity com as permissões concedidas
    }

 */





}
