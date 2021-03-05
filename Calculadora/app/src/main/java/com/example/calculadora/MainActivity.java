package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public Button Btn_0, Btn_1, Btn_2, Btn_3, Btn_4, Btn_5, Btn_6, Btn_7, Btn_8, Btn_9, Btn_Soma,
                   Btn_Menos, Btn_Igual, Btn_Multi, Btn_Div, Btn_Ponto, Btn_C, Btn_Back;
    public String[] text = new String [15];
    public String[] textF = new String [15];
    public String[] textCopia = new String[15];
    public String caso, text1 = "", text2 = "", text3 = "", operando, resultado, atual;
    public boolean op = true;
    public int i, cont = 0, cont2 = 0, textConvertido1, textConvertido2, textFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn_0 = findViewById(R.id.Btn_0);
        Btn_1 = findViewById(R.id.Btn_1);
        Btn_2 = findViewById(R.id.Btn_2);
        Btn_3 = findViewById(R.id.Btn_3);
        Btn_4 = findViewById(R.id.Btn_4);
        Btn_5 = findViewById(R.id.Btn_5);
        Btn_6 = findViewById(R.id.Btn_6);
        Btn_7 = findViewById(R.id.Btn_7);
        Btn_8 = findViewById(R.id.Btn_8);
        Btn_9 = findViewById(R.id.Btn_9);
        Btn_Menos = findViewById(R.id.Btn_Menos);
        Btn_Soma = findViewById(R.id.Btn_Soma);
        Btn_Div = findViewById(R.id.Btn_Div);
        Btn_Multi = findViewById(R.id.Btn_Multi);
        Btn_Ponto = findViewById(R.id.Btn_Ponto);
        Btn_C = findViewById(R.id.Btn_C);
        Btn_Back = findViewById(R.id.Btn_Back);
        Btn_Igual = findViewById(R.id.Btn_Igual);

        Btn_0.setOnClickListener(this);
        Btn_1.setOnClickListener(this);
        Btn_2.setOnClickListener(this);
        Btn_3.setOnClickListener(this);
        Btn_4.setOnClickListener(this);
        Btn_5.setOnClickListener(this);
        Btn_6.setOnClickListener(this);
        Btn_7.setOnClickListener(this);
        Btn_8.setOnClickListener(this);
        Btn_9.setOnClickListener(this);
        Btn_Menos.setOnClickListener(this);
        Btn_Soma.setOnClickListener(this);
        Btn_Div.setOnClickListener(this);
        Btn_Multi.setOnClickListener(this);
        Btn_Ponto.setOnClickListener(this);
        Btn_C.setOnClickListener(this);
        Btn_Back.setOnClickListener(this);
        Btn_Igual.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Calculadora calculadora = new Calculadora();
        TextView calc = findViewById(R.id.Calculadora);
        switch (view.getId()) {
            case R.id.Btn_0:
                cont = 0;
                caso = "0";
                operando = "";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_1:
                cont = 0;
                caso = "1";
                operando = "";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_2:
                cont = 0;
                caso = "2";
                operando = "";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_3:
                cont = 0;
                caso = "3";
                operando = "";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_4:
                cont = 0;
                caso = "4";
                operando = "";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_5:
                cont = 0;
                caso = "5";
                operando = "";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_6:
                cont = 0;
                caso = "6";
                operando = "";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_7:
                cont = 0;
                caso = "7";
                operando = "";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_8:
                cont = 0;
                caso = "8";
                operando = "";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_9:
                cont = 0;
                caso = "9";
                operando = "";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_Back:
                cont = 0;
                caso = "back";
                operando = "<";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_C:
                cont = 0;
                caso = "c";
                operando = "c";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_Div:
                cont = 0;
                op = true;
                caso = "div";
                operando = "/";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_Igual:
                cont = 0;
                caso = "igual";
                operando = "=";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_Menos:
                cont = 0;
                op = true;
                caso = "menos";
                operando = "-";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_Multi:
                cont = 0;
                op = true;
                caso = "multi";
                operando = "*";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_Ponto:
                cont = 0;
                caso = "ponto";
                operando = ".";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
            case R.id.Btn_Soma:
                cont = 0;
                op = true;
                caso = "soma";
                operando = "+";
                resultado = textArray(caso, operando);
                calculadora.setText(resultado);
                calc.setText(calculadora.getText());
                cont= 0;
                break;
        }
    }

    public String textArray (String caso, String operando) {
        if ((text[0] != null) && (operando.equals("+") || operando.equals("-") || operando.equals("*") || operando.equals("/")) && op) {
            op = false;
            cont2++;
            atual = operando;
            return text3 = text1 + operando;
        }

        switch (operando) {
            case "=":
                if (!text2.equals("")) {
                    textConvertido1 = Integer.parseInt(text1);
                    textConvertido2 = Integer.parseInt(text2);
                    switch (atual) {
                        case "+":
                            textFinal = textConvertido1 + textConvertido2;
                            break;
                        case "-":
                            textFinal = textConvertido1 - textConvertido2;
                            break;
                        case "*":
                            textFinal = textConvertido1 * textConvertido2;
                            break;
                        default:
                            textFinal = textConvertido1 / textConvertido2;
                            break;
                    }
                    resetArray(textF, text);
                    op = true;
                    return Integer.toString(textFinal);
                }
            case "c":
                resetArray(text, textF);
                op = true;
                cont2 = 0;
                return text1;
            case "<":
                if (textF[0] != null) {
                    text2 = "";
                    text3 = "";
                    text3 = text1 + atual;
                    Arrays.fill(textCopia, null);
                    if (textF[1] != null) {
                        for (i = 0; i < textF.length - 1; i++) {
                            if (textF[i + 1] != null) {
                                text2 = text2 + textF[i];
                                text3 = text3 + textF[i];
                                textCopia[i] = textF[i];
                            }
                        }
                    }
                    Arrays.fill(textF, null);
                    if (textCopia[0] != null) {
                        for (i = 0; i < textF.length; i++) {
                            textF[i] = textCopia[i];
                        }
                    }
                    if (textF[0] != null) {
                        return text3;
                    } else {
                        return text3;
                    }
                }
                if (text[0] != null) {
                    Arrays.fill(textCopia, null);
                    if (cont2 == 1) {
                        cont2--;
                        op = true;
                        return text1;
                    }
                    text1 = "";
                    text3 = "";
                    if (text[1] != null) {
                        for (i = 0; i < text.length - 1; i++) {
                            if (text[i + 1] != null) {
                                text1 = text1 + text[i];
                                textCopia[i] = text[i];
                            }
                        }
                    }
                    Arrays.fill(text, null);
                    if (textCopia[0] != null) {
                        for (i = 0; i < text.length; i++) {
                            text[i] = textCopia[i];
                        }
                    }
                    if (text[0] != null) {
                        return text1;
                    } else {
                        return text1;
                    }
                }
        }
        if (op && (!operando.equals("<") && !operando.equals("+") && !operando.equals("-") && !operando.equals("*") && !operando.equals("/"))) {
            for (i = 0; i < text.length; i++) {
                if (text[i] == null && cont == 0) {
                    cont++;
                    text[i] =  caso;
                    return text1 = text1 + text[i];
                }
            }
        }
        if (!text3.equals("") && (!operando.equals("<") && !operando.equals("+") && !operando.equals("-") && !operando.equals("*") && !operando.equals("/"))) {
            for (i = 0; i < textF.length; i++) {
                if (textF[i] == null && cont == 0) {
                    cont++;
                    textF[i] = caso;
                    text2 = text2 + textF[i];
                    return text3 = text3 + textF[i];
                }
            }
        }
        if (!text3.equals("")) {
            return text3;
        } else {
            return text1;
        }
    }

    public void resetArray (String[] textF, String[] text){
        text1 = "";
        text2 = "";
        text3 = "";
        Arrays.fill(textF, null);
        Arrays.fill(text, null);
    }
}