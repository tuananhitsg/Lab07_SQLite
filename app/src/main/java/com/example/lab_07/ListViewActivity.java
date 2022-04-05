package com.example.lab_07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    private Button btnAdd, btnRemove, btnCancel;
    private List<Person> personList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        btnAdd = findViewById(R.id.lvLayout_btnAdd);
        btnRemove = findViewById(R.id.lvLayout_btnRemove);
        btnCancel = findViewById(R.id.lvLayout_btnCancel);
        listView = findViewById(R.id.lvLayout_lv);
        EditText editText = findViewById(R.id.lvLayout_edtSearch);

        DatabaseHandler db = new DatabaseHandler(this);


        personList = new ArrayList<>();
        db.addPerson(new Person( "Huynh Tuan Anh"));
        db.addPerson(new Person("Tuan"));
        db.addPerson(new Person( "name"));
        List<Person> persons = db.getAllPerson();

        PersonAdapter adapter = new PersonAdapter(this, R.layout.lv_layout_item, persons);
        listView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                db.addPerson(new Person(name));
                adapter.notifyDataSetChanged();

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int index = i;

                personList.remove(i);

            }
        });


    }
}