package com.example.a15056112.todolist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddListsActivity extends AppCompatActivity {

    EditText etName, etDesc;
    Button btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lists);

        etName = (EditText)findViewById(R.id.etName);
        etDesc = (EditText)findViewById(R.id.etDesc);
        btnAdd = (Button)findViewById(R.id.buttonAdd);
        btnCancel = (Button)findViewById(R.id.buttonCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(AddListsActivity.this);
                Intent intent = new Intent();
                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();
                db.insertLists(name,desc);
                setResult(RESULT_OK, intent);
                db.close();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
    }
}
