package com.michtu.premierleague;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PlayersActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText editName, editSurname, editNickname, editAverage,editID;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myDB = new DatabaseHelper(this);

        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editNickname = (EditText)findViewById(R.id.editText_nickname);
        editAverage = (EditText)findViewById(R.id.editText_average);
        editID = (EditText)findViewById(R.id.editText_ID);
        btnAddData = (Button)findViewById(R.id.button_add);
        btnViewAll = (Button)findViewById(R.id.button_viewAll);
        btnUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();

    }

    public void DeleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDB.deleteData(editID.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(PlayersActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PlayersActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }


    public void UpdateData(){
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated = myDB.updateData(editID.getText().toString(), editName.getText().toString(), editSurname.getText().toString(), editNickname.getText().toString(), editAverage.getText().toString());
                        if(isUpdated = true)
                            Toast.makeText(PlayersActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                            else
                            Toast.makeText(PlayersActivity.this, "Data not Updated", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDB.insertData(editName.getText().toString(), editSurname.getText().toString(), editNickname.getText().toString(), editAverage.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(PlayersActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(PlayersActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );

    }

    public void viewAll(){
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDB.getAllData();
                        if(res.getCount() == 0){
                            //show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id: " + res.getString(0) + "\n");
                            buffer.append("Name: " + res.getString(1) + "\n");
                            buffer.append("Surname: " + res.getString(2) + "\n");
                            buffer.append("Nickname: " + res.getString(3) + "\n");
                            buffer.append("Average: " + res.getString(4) + "\n\n");
                        }
                        //Show all data
                        showMessage("Data", buffer.toString());

                    }
                }
        );
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
