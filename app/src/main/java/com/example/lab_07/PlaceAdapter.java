package com.example.lab_07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class PlaceAdapter extends BaseAdapter {
    private int idLayout;
    private List<Place> placeList;
    private Context context;

    public PlaceAdapter(Context context, int idLayout, List<Place> placeList) {
        this.context = context;
        this.placeList = placeList;
        this.idLayout = idLayout;
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
            tvId.setText(String.valueOf(place.getId()));
            int index = i + 1;
            tvName.setText(index + ". " + place.getName());

            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText edt = view.findViewById(R.id.customLv_edt);
                    String name = place.getName();
                    edt.setText(name);
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlaceDatabaseHandler db = new PlaceDatabaseHandler(context);
                    int id = place.getId();
                    db.deletePlace(id);
                }
            });
        }

        return view;
    }
}
