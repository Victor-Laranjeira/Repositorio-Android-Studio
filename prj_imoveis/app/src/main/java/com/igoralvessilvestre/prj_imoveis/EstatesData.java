package com.igoralvessilvestre.prj_imoveis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class EstatesData extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "bd_imovel";
    private static  final int VERSAO = 1;

    public static final String NOME_TABELA = "imovel";
    public static final String ID = "id";
    public static final String TIPO = "tipo";
    public static final String TAMANHO = "tamanho";
    public static final String TELEFONE = "telefone";
    public static final String STATUS = "status";
    public static final String LATITUDE = "latitude";
    public static  final String LONGITUDE = "longitude";

    public EstatesData(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        String CRIAR_COLUNA = "CREATE TABLE "+NOME_TABELA+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                             +TIPO+" VARCHAR NOT NULL, "
                                                             +TAMANHO+" VARCHAR NOT NULL, "
                                                             +TELEFONE+" VARCHAR NOT NULL, "
                                                             +STATUS+" VARCHAR NOT NULL, "
                                                             +LATITUDE+" DOUBLE, "
                                                             +LONGITUDE+" DOUBLE);";
        db.execSQL(CRIAR_COLUNA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int NewV) {
        db.execSQL("DROP TABLE IF EXISTS "+NOME_TABELA);
        onCreate(db);
    }

    public void addConteudo(Context context, String tipo, String tamanho, String numero, String emConstrucao, double latitude, double longitude){
        Estate e = new Estate(tipo, tamanho, numero, emConstrucao);
        e.latitude = latitude;
        e.longitude = longitude;
     //   e.addContext(context);
        ContentValues cv = new ContentValues();
        cv.put(TIPO, e.TYPE);
        cv.put(TAMANHO, e.SIZE);
        cv.put(TELEFONE, e.PHONE);
        cv.put(STATUS, e.STATUS);
        cv.put(LATITUDE, e.latitude);
        cv.put(LONGITUDE, e.longitude);
        long newRowId = getWritableDatabase().insert(NOME_TABELA, null, cv);
        //teste();
        Log.v("New", ""+newRowId);
    }
    public void deletarUm(int id){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(NOME_TABELA, ID+" = "+id, null);
    }
    public void deletarTodos(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+NOME_TABELA);
    }

    public List<Estate> inserirImoveis(){
        List<Estate> listaImoveis = new ArrayList<Estate>();
        String query = "SELECT * FROM "+NOME_TABELA;

        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
       while (c.moveToNext()){
           Estate e = new Estate(c.getString(1), c.getString(2), c.getString(3), c.getString(4));
           e.latitude = c.getDouble(5);
           e.longitude = c.getDouble(6);
           listaImoveis.add(e);
       }
        return listaImoveis;
    }




}
