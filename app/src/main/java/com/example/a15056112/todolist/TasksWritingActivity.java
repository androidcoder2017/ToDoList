package com.example.a15056112.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class TasksWritingActivity extends AppCompatActivity {

    Button btnCancel, btnEdit, btnDelete;
    EditText etDesc;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_writing);

        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnEdit = (Button)findViewById(R.id.btnSave);
        etDesc = (EditText)findViewById(R.id.etDescription);

        intent = getIntent();
        final Lists data = (Lists) intent.getSerializableExtra("data");

        etDesc.setText(data.getDescription());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(TasksWritingActivity.this);
                data.setDescription(etDesc.getText().toString());
                dbHelper.updateLists(data);
                dbHelper.close();
                setResult(RESULT_OK, intent);
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
}
