package com.example.demooflistwithhandler;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {
    Button btnadd;
    ListView studentList;
    SQLiteDatabase db;
    DataBaseHelper dhelper;
    private AlertDialog.Builder build;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnadd = (Button) findViewById(R.id.btnAdd);
        studentList = (ListView) findViewById(R.id.List);

        dhelper = new DataBaseHelper(this);
        DisplayData();

        btnadd.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),
                        AddDataActivity.class);
                startActivity(i);
            }
        });
        studentList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), AddDataActivity.class);
                Student s = (Student) parent.getItemAtPosition(position);
                //Student s=getdata.get(position);
                i.putExtra("ID", s.getId());
                i.putExtra("NAME", s.getSname());
                i.putExtra("EMAIL", s.getEmail());
                startActivity(i);

            }
        });

        studentList.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final Student stu = (Student) parent.getItemAtPosition(position);
                build = new AlertDialog.Builder(MainActivity.this);
                build.setTitle("DELETE RECORD !..");
                build.setIcon(R.drawable.delete);
                build.setMessage("Are you sure you want to delete?  " + "   ID " + stu.getId() + " | " + stu.getSname());
                build.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dhelper.Deletestudent(stu);

                        Toast.makeText(getApplicationContext(), stu.getId() + " | " + stu.getSname() + " is Deleted", 3000).show();
                        DisplayData();

                        dialog.cancel();

                    }
                });
                build.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        dialog.cancel();
                    }
                });
                build.create().show();
                return true;
            }
        });
    }

    public void DisplayData() {
        List<Student> getdata = dhelper.getallstudent();

        if (null != getdata) {

            DisplayAdapter da = new DisplayAdapter(getApplicationContext(), getdata);
            studentList.setAdapter(da);
        }
    }
}
