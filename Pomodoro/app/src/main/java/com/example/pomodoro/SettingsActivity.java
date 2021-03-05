package com.example.pomodoro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pomodoro.model.SettingsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsActivity extends AppCompatActivity {

    private SettingsModel settingsModel;
    private List<String> alarmList = new ArrayList<>();
    private Map<String, String> tempListAlarm = new HashMap<>();

    public SettingsActivity(){
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        assembleList();
        settingsModel = new SettingsModel();

        final Button salvar = (Button) findViewById(R.id.btn_Salvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText campoTitulo = findViewById(R.id.edt_Titulo);
                String titulo = campoTitulo.getText().toString();
                settingsModel.setTitulo(titulo);
                EditText campoTexto = findViewById(R.id.edt_Texto);
                String  texto = campoTexto.getText().toString();
                settingsModel.setTexto(texto);
                Spinner campoAlarme =  findViewById(R.id.cnt_Spinner);
                String alarme = campoAlarme.getSelectedItem().toString();
                String uri = setAlarme(alarme);
                settingsModel.setNomeAlarme(uri);
                Intent it = new Intent(SettingsActivity.this, MainActivity.class);
                it.putExtra("settingsModel", settingsModel);
                SettingsActivity.this.startActivity(it);
            }
        });
    }

    private String setAlarme(String alarme) {
        return "content://media/internal/audio/media" + "/" +  tempListAlarm.get(alarme);
    }

    public void assembleList() {
        RingtoneManager manager = new RingtoneManager(this);
        manager.setType(RingtoneManager.TYPE_ALARM);
        Cursor cursor = manager.getCursor();

        while (cursor.moveToNext()) {
            String notificationTitle = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX);
            String notificatioURI = cursor.getString(RingtoneManager.ID_COLUMN_INDEX);
            alarmList.add(notificationTitle);

            tempListAlarm.put(notificationTitle, notificatioURI);

        }

        Spinner s = (Spinner) findViewById(R.id.cnt_Spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, alarmList);
        s.setAdapter(adapter);

    }
}