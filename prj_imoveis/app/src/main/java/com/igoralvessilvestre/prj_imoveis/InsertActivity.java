package com.igoralvessilvestre.prj_imoveis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.INTERNET;

public class InsertActivity extends AppCompatActivity implements LocationListener {

    private final String[] PERMISSIONS = {"android.permission.INTERNET", "android.permission.ACCESS_FINE_LOCATION",
            "android.permission.ACCESS_COARSE_LOCATION"};
    private static final int PERMISSION_REQUEST_CODE = 200;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    private EstatesData db;
    private Button btn_pronto;
    private EditText telefone;
    private RadioGroup radioIdTipo;
    private RadioGroup radioIdTamanho;
    private CheckBox checkBox;

    private double latitude;
    private double longitude;
    private String provider;
    private LocationManager locMng;

    private TextView txt;

    private RadioButton tipo;
    private RadioButton tamanho;
    private String numero;
    private String emConstrucao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        pegarLocation();
        radioIdTipo = findViewById(R.id.tipo);
        radioIdTamanho = findViewById(R.id.tamanho);
        btn_pronto = (Button)findViewById(R.id.btn_pronto);
        checkBox = (CheckBox) findViewById(R.id.construcao);

        db = new EstatesData(InsertActivity.this);
        /*SQLiteDatabase helper = db.getWritableDatabase();
        helper.execSQL("DELETE FROM "+db.NOME_TABELA);*/

        btn_pronto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                telefone = findViewById(R.id.telefone);
                tipo = findViewById(radioIdTipo.getCheckedRadioButtonId());
                tamanho = findViewById(radioIdTamanho.getCheckedRadioButtonId());
                numero = telefone.getText().toString();

                if(checkBox.isChecked()){
                    emConstrucao = getString(R.string.emConstrucao);
                }else{
                    emConstrucao = "Pronto";
                }
             if(telefone.getText().length() == 8 && tipo != null && tamanho != null) {
                 popUpCam();
                 db.addConteudo(InsertActivity.this, tipo.getText().toString(), tamanho.getText().toString(), numero, emConstrucao, latitude, longitude);
                 Toast.makeText(InsertActivity.this, R.string.info_salva, Toast.LENGTH_LONG).show();

             }else{
                 Toast.makeText(InsertActivity.this, R.string.falta_param, Toast.LENGTH_LONG).show();
             }
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (checkPermission()) {
            locMng.removeUpdates(this);
            locMng.requestLocationUpdates(provider, 400, 1, this);
        } else {
            requestPermission();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        locMng.removeUpdates(this);
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    public void popUpCam(){
        AlertDialog.Builder builder = new AlertDialog.Builder(InsertActivity.this);
        builder.setCancelable(true);
        builder.setMessage(R.string.tirar_foto);
        builder.setPositiveButton(R.string.Sim,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intentCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                        startActivityForResult(intentCam, REQUEST_IMAGE_CAPTURE);
                    }
                });
        builder.setNegativeButton(R.string.Nao, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(InsertActivity.this, MainActivity.class));
                InsertActivity.this.finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();


    }



    @Override
    public void onLocationChanged(@NonNull Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        Log.v("coord", ""+latitude+" "+longitude);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            InsertActivity.this.finish();
            startActivity(new Intent(InsertActivity.this, MainActivity.class));


        }
    }

    private void pegarLocation(){
        locMng = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        provider = locMng.getBestProvider(criteria, false);

        if (checkPermission()) {
            Location location = locMng.getLastKnownLocation(provider);
            if (location != null) {
                onLocationChanged(location);
            } else {
               Toast.makeText(InsertActivity.this, R.string.loc_indisponivel, Toast.LENGTH_LONG).show();
            }
        } else {
            requestPermission();
        }
    }



    private boolean checkPermission(){
        int internetResult = ContextCompat.checkSelfPermission(getApplicationContext(), INTERNET);
        int fineLocationResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int coarseLocationResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_COARSE_LOCATION);
        Log.v("perm", ""+internetResult+" "+fineLocationResult+" "+coarseLocationResult);

        return internetResult == PackageManager.PERMISSION_GRANTED &&
                fineLocationResult == PackageManager.PERMISSION_GRANTED &&
                coarseLocationResult == PackageManager.PERMISSION_GRANTED;
    }

    private  void requestPermission(){
        ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_REQUEST_CODE );
        this.recreate();  //recria a activity com as permissões concedidas
    }

}