package com.example.lab_07;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab_07.adapter.PlaceAdapter;
import com.example.lab_07.db.PlaceDatabaseHandler;
import com.example.lab_07.model.Place;

import java.util.ArrayList;
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

    private static CustomLV instance;
    private static PlaceDatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_lv);

        instance = this;

        btnSave = findViewById(R.id.customLvLayout_btnSave);
        btnCancel = findViewById(R.id.customLvLayout_btnCancel);
        edt = findViewById(R.id.customLv_edt);
        listView = findViewById(R.id.customLVLayout_lv);

        db = new PlaceDatabaseHandler(this);

        placeList = new ArrayList<>();
        placeList = db.getAllPlace();
        adapter = new PlaceAdapter(this, R.layout.customlv_layout_item, placeList, edt);
        listView.setAdapter(adapter);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placeName = edt.getText().toString();
                if (placeName.equals("") || placeName.isEmpty()) {
                    Toast.makeText(context, "Place không được để trỗng", Toast.LENGTH_LONG).show();
                } else {
                    int selectedId = adapter.getSelectedId();
                    if(selectedId==-1){ Place place = new Place(edt.getText().toString());
                        db.addPlace(new Place(placeName));
                        edt.setText("");
                        Toast.makeText(context, "Thêm thành công", Toast.LENGTH_LONG).show();
                    }else {
                        db.updatePlace(new Place(selectedId,placeName));
                        Toast.makeText(context, "Sủa thành công", Toast.LENGTH_LONG).show();
                    }
                    update();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt.setText("");
                adapter.setSelectedId(-1);
            }
        });
    }

    public static CustomLV getInstance() {
        return instance;
    }

    public static PlaceDatabaseHandler getDb() {
        return db;
    }

    public void update() {
        placeList = db.getAllPlace();
        adapter = new PlaceAdapter(context, R.layout.customlv_layout_item, placeList, edt);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
