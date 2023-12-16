package com.example.svu_reports.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.svu_reports.R;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String[] title;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, String[] title) {
        this.context = applicationContext;
        this.title = title;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int i) {
        return title[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.grid_view_home, null);

        TextView title = (TextView) view.findViewById(R.id.titleGrid);
        title.setText(this.title[i]);// t the reference of ImageView
        return view;
    }
}
