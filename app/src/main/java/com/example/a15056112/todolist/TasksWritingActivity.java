package com.example.a15056112.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

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
                AlertDialog.Builder builder = new AlertDialog.Builder(TasksWritingActivity.this);

                builder.setTitle("Edit");
                builder.setMessage("Edited confirm?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbHelper = new DBHelper(TasksWritingActivity.this);
                        data.setDescription(etDesc.getText().toString());
                        dbHelper.updateLists(data);
                        dbHelper.close();
                        setResult(RESULT_OK, intent);
                        finish();
                        Toast.makeText(TasksWritingActivity.this, " '" + data.getName() + "' have been edited", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("No",null);
                builder.show();



            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TasksWritingActivity.this);

                builder.setTitle("Delete");
                builder.setMessage("Are you sure you want to delete '" + data.getName() + "'?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbHelper = new DBHelper(TasksWritingActivity.this);
                        dbHelper.deleteLists(data.getId());
                        dbHelper.close();
                        setResult(RESULT_OK, intent);
                        finish();
                        Toast.makeText(TasksWritingActivity.this, " '" + data.getName() + "' is deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("No",null);
                builder.show();


            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TasksWritingActivity.this);

                builder.setTitle("Cancel");
                builder.setMessage("Are you sure you want to cancel?");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        setResult(RESULT_CANCELED, intent);
                        finish();
                        Toast.makeText(TasksWritingActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNeutralButton("No",null);
                builder.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Exit");
        builder.setMessage("Are you sure you want to exit?");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
                Toast.makeText(TasksWritingActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("No",null);
        builder.show();

    }

}
