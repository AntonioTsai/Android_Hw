package com.example.antonio.lab7;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {
    private Button newBtn, delBtn, updateBtn, queryBtn;
    private ListView listView;
    private EditText idEdt, nameEdt, phoneEdt;
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
        idEdt = (EditText) findViewById(R.id.uid);
        nameEdt = (EditText) findViewById(R.id.name);
        phoneEdt = (EditText) findViewById(R.id.phone);

        try { // Create Database
            db = openOrCreateDatabase("example.db", MODE_PRIVATE, null);
        } catch (Exception e) {

        }

        try { // Create a table "contact"
            db.execSQL("DROP TABLE contact");
            db.execSQL("CREATE TABLE contact (_id INTEGER PRIMARY KEY, name TEXT, phone TEXT)");
        } catch (Exception e) {
            db.execSQL("CREATE TABLE contact (_id INTEGER PRIMARY KEY, name TEXT, phone TEXT)");
        }

        newBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ContentValues cv = new ContentValues();
                cv.put("name", nameEdt.getText().toString());
                cv.put("phone", phoneEdt.getText().toString());
                db.insert("contact", null, cv);
                loadListView("SELECT * FROM contact");
            }
        });
    }

    private void loadListView(String s) {
        Cursor cursor = db.rawQuery(s, null);
        if (cursor != null && cursor.getCount() >= 0) {
            SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                    cursor, new String[]{"name", "phone"},
                    new int[]{android.R.id.text1, android.R.id.text2}, 0);
            listView.setAdapter(adapter);
        }
    }
}
