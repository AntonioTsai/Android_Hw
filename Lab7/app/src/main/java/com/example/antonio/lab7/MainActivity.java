package com.example.antonio.lab7;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private Button newBtn, delBtn, updateBtn, queryBtn;
    private ListView listView;
    private EditText IdEdt, nameEdt, phoneEdt;
    private SQLiteDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newBtn = (Button) findViewById(R.id.create);
        delBtn = (Button) findViewById(R.id.delete);
        updateBtn = (Button) findViewById(R.id.update);
        queryBtn = (Button) findViewById(R.id.search);
        listView = (ListView) findViewById(R.id.ListView);
        IdEdt = (EditText) findViewById(R.id.uid);
        nameEdt = (EditText) findViewById(R.id.name);
        phoneEdt = (EditText) findViewById(R.id.phone);
    }
}
