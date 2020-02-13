package com.example.databasespinner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbhelp;
    private SQLiteDatabase db;
    private MyDB md;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.dbhelp= new MyDatabaseHelper(this);
        this.db=dbhelp.getWritableDatabase();
        this.md= new MyDB(this);
        Spinner desplegable = (Spinner) this.findViewById(R.id.spinnerCategories);
        try {
            md.createRecords("1","primer");
            md.createRecords("2","segon");
            md.createRecords("3","tercer");
            md.createRecords("4","quart");
        }catch(Exception e){
            Log.d("patata", "La entrada ja existeix");
        }
        Cursor c =md.selectRecords();
        startManagingCursor(c);
        String[] from = new String[]{"name"};
        int[] to = new int[]{android.R.id.text1};
        SimpleCursorAdapter mAdapter = new SimpleCursorAdapter (this, android.R.layout.simple_spinner_item, c, from, to);
        desplegable.setAdapter(mAdapter);
    }
}
