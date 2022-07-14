package com.example.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycleView;
    RecyclerContactAdapter adapter;
    FloatingActionButton btnOpenDialog;

    ArrayList<ContactModel> arrContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleView = findViewById(R.id.recyclerContact);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);



        btnOpenDialog.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_add_contact);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtContact = dialog.findViewById(R.id.edtContact);
                Button btnAction = dialog.findViewById(R.id.btnAdd);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="", number="";

                        if(!edtName.getText().toString().equals("")) {
                            name = edtName.getText().toString();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Please Enter Contact Name", Toast.LENGTH_LONG).show();
                        }

                        if(!edtContact.getText().toString().equals("")){
                            number = edtContact.getText().toString();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Please Enter Contact Number", Toast.LENGTH_LONG).show();
                        }

                        arrContacts.add(new ContactModel(R.drawable.avatar1, name, number));

                        adapter.notifyItemInserted(arrContacts.size()-1);

                        recycleView.scrollToPosition(arrContacts.size()-1);

                        dialog.dismiss();

                    }
                });

                dialog.show();
            }
        });


       recycleView.setLayoutManager(new LinearLayoutManager(this));

        //    Use either this method for setting the values or you can directly add in the arrContacts functions
        //    ContactModel model = new ContactModel(R.drawable.anupam, "Anupam Chaudhary", "9870797329");
        //    arrContacts.add(model);

        arrContacts.add(new ContactModel(R.drawable.anupam, "Anupam Chaudhary", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar1, "A", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar2, "B", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar3, "C", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar4, "D", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar5, "E", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar6, "F", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar7, "G", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar8, "H", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar9, "I", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar10, "J", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar11, "K", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar12, "L", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar13, "M", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avatar14, "N", "9870797329"));
        arrContacts.add(new ContactModel(R.drawable.avaatar15, "O", "9870797329"));


        adapter = new RecyclerContactAdapter(MainActivity.this, arrContacts);
        recycleView.setAdapter(adapter);

    }
}