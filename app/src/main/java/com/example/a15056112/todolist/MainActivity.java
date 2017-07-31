package com.example.a15056112.todolist;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int reqCode = 1;
    //FloatingActionButton fab;
    Button btnAdd;
    ListView lvLists;
    TextView tvTitleList;
    ArrayList<Lists> alLists;
    ListsAdapter aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //fab = (FloatingActionButton)findViewById(R.id.fab);

        tvTitleList = (TextView)findViewById(R.id.tvTitleList);
        btnAdd = (Button)findViewById(R.id.add);
        lvLists = (ListView)findViewById(R.id.lvLists);

        tvTitleList.setText(Html.fromHtml("<h1><u>List of Different Tasks</u></h1>"));

        alLists = new ArrayList<Lists>();
        aa = new ListsAdapter(MainActivity.this, R.layout.row, alLists);

        final DBHelper db = new DBHelper(MainActivity.this);
        alLists.addAll(db.getAllLists());
        //alLists = db.getAllLists();

        lvLists.setAdapter(aa);

        aa.notifyDataSetChanged();

        db.close();

        lvLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TasksWritingActivity.class);

                Lists target = new Lists(db.getAllLists().get(position).getId(), db.getAllLists().get(position).getName(), db.getAllLists().get(position).getDescription());
                intent.putExtra("data",target);
                startActivityForResult(intent, reqCode);
            }
        });

        /*
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddListsActivity.class);
                startActivityForResult(intent, reqCode);
            }
        }); */


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddListsActivity.class);
                startActivityForResult(intent, reqCode);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);

        if (resultCode == RESULT_OK && requestCode == this.reqCode) {
            DBHelper db = new DBHelper(MainActivity.this);
            alLists = db.getAllLists();
            aa = new ListsAdapter(MainActivity.this, R.layout.row, alLists);
            lvLists.setAdapter(aa);
        }

    }
}
