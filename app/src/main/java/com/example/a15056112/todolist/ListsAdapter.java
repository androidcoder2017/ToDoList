package com.example.a15056112.todolist;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15056112 on 31/7/2017.
 */

public class ListsAdapter extends ArrayAdapter<Lists> {

    Context context;
    ArrayList<Lists> lists;
    int resource;
    TextView tvTaskTitle, tvDescTasks, titleTask;

    public ListsAdapter(Context context, int resource, ArrayList<Lists> list) {
        super(context, resource, list);
        this.context = context;
        this.resource = resource;
        this.lists = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.row, parent, false);

        tvTaskTitle = (TextView)view.findViewById(R.id.tvNameTitle);
        tvDescTasks = (TextView)view.findViewById(R.id.tvDescTasks);
        titleTask = (TextView)view.findViewById(R.id.titleTask);

        Lists wholeLists =  lists.get(position);
        tvTaskTitle.setText(wholeLists.getName());
        tvDescTasks.setText(wholeLists.getDescription());

        if (position % 2 == 0) {
            view.setBackgroundColor(Color.DKGRAY);
            tvTaskTitle.setTextColor(Color.WHITE);
            tvDescTasks.setTextColor(Color.WHITE);
            titleTask.setTextColor(Color.WHITE);
        } else {
            view.setBackgroundColor(Color.GRAY);
        }

        return view;
    }
}
