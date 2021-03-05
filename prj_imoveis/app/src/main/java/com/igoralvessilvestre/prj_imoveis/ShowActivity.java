package com.igoralvessilvestre.prj_imoveis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ShowActivity extends AppCompatActivity {

    private ListView list;
    private SQLiteDatabase helper;
    private EstatesData db;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrList;
    private List<Estate> imoveis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        list = (ListView)findViewById(R.id.lista);

        db = new EstatesData(this);
        helper = db.getWritableDatabase();
        listarImoveis();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ShowActivity.this, ShowAddressesActivity.class);
                Estate e = imoveis.get(i);
               // intent.putExtra("estate", e);
                intent.putExtra("latitude", e.latitude);
                intent.putExtra("longitude", e.longitude);
                intent.putExtra("phone", e.PHONE);
                startActivity(intent);
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int coluna = i;
               mostrarMenu(view, coluna);
                return true;
            }
        });

    }
    public void apagarDaLista(int i){
        String query = "SELECT "+db.ID+" FROM "+db.NOME_TABELA;
        Cursor c = helper.rawQuery(query , null);
        c.moveToPosition(i);
        db.deletarUm(c.getInt(0));
        recreate();
        Toast.makeText(ShowActivity.this, R.string.imovel_apagado, Toast.LENGTH_LONG).show();
    }

    public void listarImoveis(){
        imoveis = db.inserirImoveis();
        arrList = new ArrayList<String>();
        adapter = new ArrayAdapter<String>(ShowActivity.this, android.R.layout.simple_expandable_list_item_1, arrList);
        list.setAdapter(adapter);

        for(Estate e : imoveis){
            //Log.d("Lista",e.toString());
            arrList.add(e.toString());
            adapter.notifyDataSetChanged();
        }
    }
    public void mostrarMenu(View v, final int i){
        final PopupMenu popupMenu = new PopupMenu(ShowActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.apaga){
                    apagarDaLista(i);
                }else{
                    db.deletarTodos();
                    recreate();
                }

                return true;
            }
        });

        popupMenu.show();

    }



}