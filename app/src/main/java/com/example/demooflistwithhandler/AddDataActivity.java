package com.example.demooflistwithhandler;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract.Intents;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends Activity {
 Button btnsave,btnupdate;
 EditText editName,editEmail;
 public String name,email;
 DataBaseHelper dhelper;
public String ID, NAME, EMAIL;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_data);
		
		 
		btnsave=(Button)findViewById(R.id.save_btn);
		btnupdate=(Button)findViewById(R.id.update_btn);
		editName=(EditText)findViewById(R.id.edit_name);
		editEmail=(EditText)findViewById(R.id.edit_email);
		
		name = editName.getText().toString().trim();
		email = editEmail.getText().toString().trim();
		
		ID = getIntent().getStringExtra("ID");
		NAME = getIntent().getStringExtra("NAME");
		EMAIL = getIntent().getStringExtra("EMAIL");
		editName.setText(NAME);
		editEmail.setText(EMAIL);
		
		btnsave.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				name = editName.getText().toString().trim();
				email = editEmail.getText().toString().trim();
				DataBaseHelper dhelper=new DataBaseHelper(getApplicationContext());
				if (name.length() > 0 && email.length() > 0) {
					    Student stu=new Student(name,email);
						dhelper.insertdata(stu);
						Toast.makeText(getBaseContext(),"New Student Record has been inserted..",3000).show();
						finish();
						Intent i = new Intent(AddDataActivity.this, MainActivity.class);
					    // set the new task and clear flags
					    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
					    startActivity(i);
				} else {
					AlertDialog.Builder alertdialog = new AlertDialog.Builder(
							AddDataActivity.this);
					alertdialog.setTitle("Invalid Data");
					alertdialog.setMessage("please,Eneter valid Data!..");
					alertdialog.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							});
					alertdialog.create().show();
				}
				 
			}
		});
		btnupdate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				name = editName.getText().toString().trim();
				email = editEmail.getText().toString().trim();
				DataBaseHelper dhelper=new DataBaseHelper(getApplicationContext());
				if (name.length() > 0 && email.length() > 0) {
					Student stu=new Student(ID,name,email);
					dhelper.updatedata(stu);
					Toast.makeText(getBaseContext()," Student Record has been Updated..",3000).show();
					finish();
					Intent i = new Intent(AddDataActivity.this, MainActivity.class);
				    // set the new task and clear flags
				    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				    startActivity(i);
					
				}
			}
		});
		
	}
	
}
