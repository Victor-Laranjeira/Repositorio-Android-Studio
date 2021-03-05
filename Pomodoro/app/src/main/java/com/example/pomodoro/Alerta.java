package com.example.pomodoro;

import android.app.Activity;
import android.app.AlertDialog;

public class Alerta extends Activity {

    public Alerta(){
    }

    public void callAlertDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Título");
        alert.setMessage("Message");
        alert.show();
    }

}
