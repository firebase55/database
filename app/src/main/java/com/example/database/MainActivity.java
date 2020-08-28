package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper studentDB;

    EditText editTextName,editTextFatherName,editTextAge,editTextAddress,editTextPhoneNumner;
    Button btnAddData;
    Button btnViewAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        studentDB= new DatabaseHelper(this);

        editTextName=findViewById(R.id.editTextName);
        editTextFatherName=findViewById(R.id.editTextFatherName);
        editTextAge=findViewById(R.id.editTextAge);
        editTextAddress=findViewById(R.id.editTextAddress);
        editTextPhoneNumner=findViewById(R.id.editTextPhoneNumber);
        btnAddData=findViewById(R.id.btnAddData);
        btnViewAll=findViewById(R.id.btnViewAll);
        AddData();
        ViewAll();
    }
    public void ViewAll(){
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=studentDB.getAllData();
                if(res.getCount()==0){
                    // show message
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("studentId "+res.getString(0)+"\n");
                    buffer.append("name "+res.getString(1)+"\n");
                    buffer.append("fname "+res.getString(2)+"\n");
                    buffer.append("Age "+res.getString(3)+"\n");
                    buffer.append("Address "+res.getString(4)+"\n");
                    buffer.append("phoneNo "+res.getString(5)+"\n");
                }
                // Show all data
                showMessage("Data",buffer.toString());
            }
        });
    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void AddData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = studentDB.insertData(
                        editTextName.getText().toString(),
                        editTextFatherName.getText().toString(),
                        editTextAge.getText().toString(),
                        editTextAddress.getText().toString(),
                        editTextPhoneNumner.getText().toString());
                if(isInserted=true){
                    Toast.makeText(MainActivity.this,"Data Inserted ",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(MainActivity.this,"Data not Inserted ", Toast.LENGTH_LONG).show();

                }
            }
        });
    }
}
