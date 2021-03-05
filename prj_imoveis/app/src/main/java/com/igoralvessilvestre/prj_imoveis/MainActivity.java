package com.igoralvessilvestre.prj_imoveis;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Button btn_novo;
    private Button btn_visualizar;
    private Button btn_gravar;
    private EstatesData db;
    private Button btn_mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_novo = (Button) findViewById(R.id.btn_novo);
        btn_visualizar = (Button)findViewById(R.id.btn_visualizar);
        btn_gravar = (Button)findViewById(R.id.btn_gravar);
        btn_mapa = (Button) findViewById(R.id.mapa);
        db = new EstatesData(this);

        btn_novo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
            }
        });

        btn_visualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowActivity.class));
            }
        });

        btn_gravar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!db.inserirImoveis().isEmpty()) {
                    new Cliente(db.inserirImoveis()).execute();
                    Toast.makeText(MainActivity.this, R.string.imov_save, Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, R.string.nao_imov_sav, Toast.LENGTH_LONG).show();
                }
            }
        });
        btn_mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!db.inserirImoveis().isEmpty()) {
                    startActivity(new Intent(MainActivity.this, ShowAddressesActivity.class));
                }
            }
        });
    }

}