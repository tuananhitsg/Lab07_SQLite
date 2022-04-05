package com.example.lab_07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PersonAdapter extends BaseAdapter {
    private Context context;
    private List<Person> personList;
    private int idLayout;

    public PersonAdapter(Context context, int idLayout, List<Person> personList) {
        this.context = context;
        this.personList = personList;
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

        TextView tv = view.findViewById(R.id.lvLayout_itemName);

        if (!personList.isEmpty() && personList != null) {
            Person user = personList.get(i);
            tv.setText(user.getName());
        }

        return view;
    }
}
