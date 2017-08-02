package com.example.a15056112.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.provider.Contacts;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int reqCode = 1;
    Button btnAdd;
    ListView lvLists;
    TextView tvTitleList;
    ArrayList<Lists> alLists;
    ListsAdapter aa;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitleList = (TextView)findViewById(R.id.tvTitleList);
        btnAdd = (Button)findViewById(R.id.add);
        lvLists = (ListView)findViewById(R.id.lvLists);


        alLists = new ArrayList<Lists>();
        aa = new ListsAdapter(MainActivity.this, R.layout.row, alLists);

        db = new DBHelper(MainActivity.this);
        alLists.addAll(db.getAllLists());

        lvLists.setAdapter(aa);

        aa.notifyDataSetChanged();

        db.close();

        lvLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TasksWritingActivity.class);
                Lists lists = alLists.get(position);
                /*Lists target = new Lists(db.getAllLists().get(position).getId(), db.getAllLists().get(position).getName(),
                        db.getAllLists().get(position).getDescription(), db.getAllLists().get(position).getDateCreated()); */

                intent.putExtra("data", lists);
                startActivityForResult(intent, reqCode);

            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddListsActivity.class);
                startActivityForResult(intent, reqCode);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search, menu);

        final MenuItem searchItem = menu.findItem(R.id.item_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final ArrayList<Lists> listsSearchList = new ArrayList<Lists>();
                ListView lvSearch;

                lvSearch = (ListView)findViewById(R.id.lvLists);
                for (Lists lists : alLists) {
                    if (lists.getName().toLowerCase().contains(newText.toLowerCase())) {
                        listsSearchList.add(lists);
                        lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Lists lists = listsSearchList.get(position);
                                Intent intent = new Intent(MainActivity.this, TasksWritingActivity.class);

                                intent.putExtra("data",lists);
                                startActivityForResult(intent, reqCode);
                            }
                        });
                    }
                }
                aa = new ListsAdapter(MainActivity.this, R.layout.row, listsSearchList);
                lvSearch.setAdapter(aa);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
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
