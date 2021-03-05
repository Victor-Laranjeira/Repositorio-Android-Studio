package com.example.pomodoro.model;


import java.io.Serializable;

public class SettingsModel implements Serializable {

    private String titulo;
    private String texto;
    private String nomeAlarme;
    private Integer contador;

    public SettingsModel(){}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNomeAlarme() {
        return nomeAlarme;
    }

    public void setNomeAlarme(String nomeAlarme) {
        this.nomeAlarme = nomeAlarme;
    }
}
