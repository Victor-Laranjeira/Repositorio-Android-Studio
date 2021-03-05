package com.example.pomodoro;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pomodoro.model.SettingsModel;

public class MainActivity extends Activity{

    private static final String CHANNEL_ID = "d";
    TextView timerTextView;
    public Contador timer;
    long milisegundos = 1500000L;
    boolean restPomo = false, restCurta = false, restLonga = false;
    boolean continuar = false;
    boolean indReiniciar = false;
    boolean contLonga = false, contCurta = false;
    private int contPomodo = 0;
    private SettingsActivity settingsActivity;
    private Ringtone r;
    private AlertDialog.Builder alert;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = (TextView) findViewById(R.id.contador);
        timerTextView.setText("25:00");

        final Button btn_Longa = (Button) findViewById(R.id.btn_longa);
        btn_Longa.setEnabled(false);

        Button b = (Button) findViewById(R.id.btn_Começar);
        b.setText("Iniciar");
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TextView contador = (TextView) findViewById(R.id.contador);
                Button b = (Button) v;
                if (b.getText().equals("Pausar")) {
                    if(timer != null){
                        timer.cancel();
                    }
                    Button comecar = (Button) findViewById(R.id.btn_Começar);
                    comecar.setText("Continuar");
                } else {
                    b.setText("Pausar");
                    if(timer == null && !continuar) {
                        timer = new Contador(contador, milisegundos, 1000);
                        timer.start();
                        continuar = true;
                        restPomo = true;
                    }else if(timer != null && continuar){
                        timer = new Contador(contador, timer.timeRemaining(), 1000);
                        timer.start();
                    } else if(indReiniciar){
                        timer = new Contador(contador, milisegundos, 1000);
                        timer.start();
                        indReiniciar = false;
                    } else{
                        timer = new Contador(contador, milisegundos, 1000);
                        timer.start();
                        final Button longa = (Button) findViewById(R.id.btn_longa);
                        longa.setEnabled(false);
                        continuar = true;
                    }

                }
            }
        });
        final Button curta = (Button) findViewById(R.id.btn_curta);
        curta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView contador = (TextView) findViewById(R.id.contador);
                contador.setText("5:00");
                milisegundos = 300000L;
                continuar = false;
                restCurta = true;
                restLonga = false;
                restPomo = false;
                contCurta = false;
                contLonga = false;
                if(timer != null){
                    timer.cancel();
                }
                Button comecar = (Button) findViewById(R.id.btn_Começar);
                comecar.setText("Iniciar");
            }
        });
        final Button longa = (Button) findViewById(R.id.btn_longa);
        longa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView contador = (TextView) findViewById(R.id.contador);
                contador.setText("30:00");
                milisegundos = 1800000L;
                continuar = false;
                restLonga = true;
                restCurta = false;
                restPomo = false;
                contLonga = false;
                contCurta = false;
                if(timer != null){
                    timer.cancel();
                }
                Button comecar = (Button) findViewById(R.id.btn_Começar);
                comecar.setText("Iniciar");
            }
        });

        final Button pomodoro = (Button) findViewById(R.id.btn_pomodoro);
        pomodoro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView contador = (TextView) findViewById(R.id.contador);
                milisegundos = 1500000L;
                contPomodoro();
                contador.setText("25:00");
                continuar = false;
                restPomo = true;
                restCurta = false;
                restLonga = false;
                contLonga = true;
                contCurta = true;
                if(timer != null){
                    timer.cancel();
                }
                Button comecar = (Button) findViewById(R.id.btn_Começar);
                comecar.setText("Iniciar");
            }
        });

        Button reiniciar = (Button) findViewById(R.id.btn_Reiniciar);
        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView contador = (TextView) findViewById(R.id.contador);
                Button comecar = (Button) findViewById(R.id.btn_Começar);
                indReiniciar = true;
                if(restPomo) {
                    if (timer != null && !contador.getText().toString().equals("25:00")) {
                        pomodoro.callOnClick();
                        timer = new Contador(contador, milisegundos, 1000);
                        timer.start();
                        comecar.setText("Pausar");
                    }
                }else if (restLonga) {
                    if (timer != null && !contador.getText().toString().equals("25:00")) {
                        longa.callOnClick();
                        timer = new Contador(contador, milisegundos, 1000);
                        timer.start();
                        comecar.setText("Pausar");
                    }
                } else if (restCurta){
                    if (timer != null && !contador.getText().toString().equals("25:00")) {
                        curta.callOnClick();
                        timer = new Contador(contador, milisegundos, 1000);
                        timer.start();
                        comecar.setText("Pausar");
                    }
                }
            }
        });

        Button config = (Button) findViewById(R.id.btn_Conf);
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(it);
            }
        });

        final TextView verificarCampo = (TextView) findViewById(R.id.contador);
        verificarCampo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void afterTextChanged(Editable editable) {
                if (verificarCampo.getText().toString().equals("00:00")) {
                    timer.cancel();
                    callAlert();
                    pomodoro.callOnClick();
                }
            }
        });

    }

    private void contPomodoro(){
        final TextView campoContado = (TextView) findViewById(R.id.contador);
        if(campoContado.getText().toString().equals("00:00")) {
            if (contPomodo < 3 && contLonga && contLonga) {
                contPomodo++;
            } else if (contLonga && contLonga) {
                final Button longa = (Button) findViewById(R.id.btn_longa);
                longa.setEnabled(true);
                contPomodo = 0;
            }
        }

    }

    private void callAlert() {
        Bundle settingsModel = getIntent().getExtras();

        alert = new AlertDialog.Builder(this);
        if (settingsModel != null) {
            SettingsModel value = (SettingsModel) settingsModel.get("settingsModel");
            if(value.getTitulo().equals("") || value.getTitulo() == null ){
                alert.setTitle("Alerta");
            } else{
                alert.setTitle(value.getTitulo());
            }
            if(value.getTexto().equals("") || value.getTexto() == null){
                alert.setMessage("Pomodoro finalizado.");
            }else {
                alert.setMessage(value.getTexto());
            }
            playRingtone(value.getNomeAlarme());
        }else {
            alert.setTitle("Alerta");
            alert.setMessage("Pomodoro finalizado.");
            playRingtone("content://media/internal/audio/media/12");
        }
        alert.setPositiveButton("FECHAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(r != null){
                    r.stop();
                }
            }
        });
        alert.setCancelable(false);
        alert.show();
    }

    private void playRingtone(String uri){
       r = RingtoneManager.getRingtone(this, Uri.parse(uri));
       r.play();
    }


}