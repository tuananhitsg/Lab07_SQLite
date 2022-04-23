package com.example.lab_07;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

public class PersonAdapter extends BaseAdapter {
    private Context context;
    private List<Person> personList;
    private int idLayout;
    private int selectedPosition = -1;
    public PersonAdapter(Context context, int idLayout, List<Person> userList) {
        this.context = context;
        this.personList = userList;
        this.idLayout = idLayout;
    }

    @Override
    public int getCount() {
        if (personList.size() != 0 && !personList.isEmpty())
            return personList.size();
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
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(idLayout, viewGroup, false);
        }

        TextView tvName = view.findViewById(R.id.lvLayout_itemName);
        Person person = personList.get(i);
        if (!personList.isEmpty() && personList != null) {

            tvName.setText(person.getName());
        }


        return view;
    }

}
