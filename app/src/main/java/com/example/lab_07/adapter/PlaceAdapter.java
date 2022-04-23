package com.example.lab_07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PlaceAdapter extends BaseAdapter {
    private int idLayout;
    private List<Place> placeList;
    private Context context;
    private EditText edt;
    private int selectedId=-1;
    public PlaceAdapter(Context context, int idLayout, List<Place> placeList, EditText edt) {
        this.context = context;
        this.placeList = placeList;
        this.idLayout = idLayout;
        this.edt = edt;
    }

    @Override
    public int getCount() {
        if (placeList.size() != 0 && !placeList.isEmpty())
            return placeList.size();
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout, viewGroup, false);

        TextView tvName = view.findViewById(R.id.customLvLayout_itemName);
        TextView tvId = view.findViewById(R.id.customLvLayout_itemId);
        ImageButton btnEdit = view.findViewById(R.id.customLvLayout_itemBtnEdit);
        ImageButton btnDelete = view.findViewById(R.id.customLvLayout_itemBtnDelete);

        if (!placeList.isEmpty() && placeList != null) {
            Place place = placeList.get(i);
//            tvId.setText(String.valueOf(place.getId()));
//            int index = i + 1;
//            tvName.setText(index + ". " + place.getName());
            int index = i + 1;
            tvId.setText(place.getId() + ".");
            tvName.setText(index+ ". " +place.getName());

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name = tvName.getText().toString().split("\\d\\.", 0)[1];
                    edt.setText(name.trim());
                    edt.setSelection(name.length()-1);
                 //   int id = Integer.parseInt(tvId.getText().toString());
                 //   selectedId = id;
                  //  edt.setSelection(name.length() - 1);
//                    int id = Integer.parseInt(tvId.getText().toString());
//                    selectedId = id;
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CustomLV instance = CustomLV.getInstance();
                    instance.getDb().deletePlace(place.getId());
                    Toast.makeText(instance, "Xoá thành công", Toast.LENGTH_SHORT).show();
                    instance.update();
                }
            });
        }
        return view;
    }
    public int getSelectedId() {
        return selectedId;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }
}
