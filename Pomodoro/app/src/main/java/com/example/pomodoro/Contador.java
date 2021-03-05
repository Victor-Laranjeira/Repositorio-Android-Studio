package com.example.pomodoro;

import android.app.AlertDialog;
import android.os.CountDownTimer;
import android.widget.TextView;
import java.util.Calendar;

public class Contador extends CountDownTimer  {

    private static final String CHANNEL_ID = "d";
    private TextView textView;
    public long timeInFuture;
    public Alerta alerta;

    public Contador( TextView textView, long TimeInFuture, long countDownInterval) {
        super(TimeInFuture, countDownInterval);
        this.textView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        timeInFuture = millisUntilFinished;
        textView.setText(getCorrectTimer(true, millisUntilFinished) + ":" + getCorrectTimer(false, millisUntilFinished));
    }

    @Override
    public void onFinish() {
        timeInFuture -= 1000;
        if (timeInFuture > 0) {
            textView.setText(getCorrectTimer(true, timeInFuture) + ":" + getCorrectTimer(false, timeInFuture));
        }else{
            this.cancel();
            alerta.callAlertDialog();
        }
    }

    public long timeRemaining(){
        return timeInFuture;
    }

    private String getCorrectTimer(boolean isMinute, long millisUntilFinished) {
        String aux;
        int constCalendar = isMinute ? Calendar.MINUTE : Calendar.SECOND;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(millisUntilFinished);

        aux = c.get(constCalendar) < 10 ? "0" + c.get(constCalendar) : "" + c.get(constCalendar);
        return (aux);
    }

}


