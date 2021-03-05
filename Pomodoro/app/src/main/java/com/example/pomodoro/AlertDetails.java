package com.example.pomodoro;
import android.widget.TextView;

import com.example.pomodoro.Contador;

public class AlertDetails extends Contador {
    public AlertDetails(TextView textView, long TimeInFuture, long countDownInterval) {
        super(textView, TimeInFuture, countDownInterval);
    }
}
