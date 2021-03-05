package com.igoralvessilvestre.prj_imoveis;

import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class Cliente extends AsyncTask<Void, Void, Void> {
    public ObjectOutputStream output;
    public ObjectInputStream input;
    public List<Estate> lista;
    private String IP = "192.168.0.102"; //ALTERAR AQUI O ENDEREÇO PARA O IPV4 DA MAQUINA QUE ESTA O SERVIDOR

    public Cliente(List<Estate> lista){
        this.lista = lista;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            Log.v("test", "Tentando conexão...");
            Socket client = new Socket(IP, 8083);
            Log.v("test", "Conectado");

            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());

            output.writeObject(lista);
            output.flush();


            output.close();
            input.close();
            client.close();
        }catch(IOException e){Log.v("error",e.getMessage());}
        return null;
    }

}
