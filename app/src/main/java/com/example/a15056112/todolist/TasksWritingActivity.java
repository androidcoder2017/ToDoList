package com.example.a15056112.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class TasksWritingActivity extends AppCompatActivity {

    Button btnCancel, btnEdit, btnDelete;
    EditText etDesc;
    TextView tvUpdated;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_writing);

        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnEdit = (Button)findViewById(R.id.btnSave);
        etDesc = (EditText)findViewById(R.id.etDescription);
        tvUpdated = (TextView)findViewById(R.id.tvUpdated);

        intent = getIntent();
        final Lists data = (Lists) intent.getSerializableExtra("data");

        etDesc.setText(data.getDescription());

        Calendar now = Calendar.getInstance();
        final String datetime = now.get(Calendar.DAY_OF_MONTH) + "/" +
                (now.get(Calendar.MONTH) + 1) + "/" +
                now.get(Calendar.YEAR) + " " +
                now.get(Calendar.HOUR_OF_DAY) + ":" +
                now.get(Calendar.MINUTE);
        tvUpdated.setText("Last updated on: " + datetime);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(TasksWritingActivity.this);
                data.setDescription(etDesc.getText().toString());
                dbHelper.updateLists(data);
                dbHelper.close();
                setResult(RESULT_OK, intent);

                SharedPreferences preferences= getSharedPreferences("saved", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("date", datetime);
                editor.commit();

                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(TasksWritingActivity.this);
                dbHelper.deleteLists(data.getId());
                dbHelper.close();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences prefs = getSharedPreferences("saved", MODE_PRIVATE);
        String dateTime = prefs.getString("date", "");
        tvUpdated.setText("Last updated on " + dateTime);
    }
}
