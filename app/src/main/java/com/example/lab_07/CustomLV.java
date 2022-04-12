package com.example.lab_07;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CustomLV extends AppCompatActivity {
    private Button btnSave, btnCancel;
    private EditText edt;
    private List<Place> placeList;
    private ListView listView;
    private Context context;
    private PlaceAdapter adapter;
    private int selectedId = -1;
    private ImageButton btnEdit, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_lv);
        context = this;

        btnSave = findViewById(R.id.customLvLayout_btnSave);
        btnCancel = findViewById(R.id.customLvLayout_btnCancel);
        edt = findViewById(R.id.customLv_edt);
        listView = findViewById(R.id.customLVLayout_lv);

        PlaceDatabaseHandler db = new PlaceDatabaseHandler(this);
        db.resetDatabase();

        db.addPlace(new Place("Đà Lạt"));
        db.addPlace(new Place("Buôn Mê Thuộc"));
        db.addPlace(new Place("Cần Thơ"));
        db.addPlace(new Place("Phú Quốc"));
        db.addPlace(new Place("Lý Sơn"));
        db.addPlace(new Place("Côn Đảo"));
        db.addPlace(new Place("Vũng Tàu"));

        placeList = db.getAllPlace();

        adapter = new PlaceAdapter(this, R.layout.customlv_layout_item, placeList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView tvId = view.findViewById(R.id.customLvLayout_itemId);
                TextView tvName = view.findViewById(R.id.customLvLayout_itemName);
                int id = Integer.parseInt(tvId.getText().toString());
                String name = tvName.getText().toString();
                edt.setText(name);
                Toast.makeText(context, name, Toast.LENGTH_LONG).show();
                selectedId = id;
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = edt.getText().toString();
                if (data.equals("") || data.isEmpty()) {
                    Toast.makeText(context, "Tên không được để trỗng", Toast.LENGTH_LONG).show();
                } else {
                    if(selectedId == -1) {
                        db.addPlace(new Place(data));
                    } else {
                        db.updatePlace(new Place(selectedId, data));
                    }
                    placeList = db.getAllPlace();
                    adapter = new PlaceAdapter(context, R.layout.lv_layout_item, placeList);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText("");
                selectedId = -1;
            }
        });
    }
}