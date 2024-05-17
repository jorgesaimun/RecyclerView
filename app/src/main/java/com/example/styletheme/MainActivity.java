package com.example.styletheme;

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
    RecyclerView recyclerView;
    RecyclerContactAdapter adapter;
    ArrayList<ContactModel> arrayContact = new ArrayList<>();
    FloatingActionButton fab_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerContact);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        fab_add = findViewById(R.id.add_fab);
        fab_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();

                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_layout);
                dialog.getWindow().setLayout(
                        RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT
                );

                EditText et_name = dialog.findViewById(R.id.et_contactName);
                EditText et_number = dialog.findViewById(R.id.et_contactNumber);

                Button btnAction = dialog.findViewById(R.id.savebtn);

                adapter = new RecyclerContactAdapter(MainActivity.this, arrayContact);
                recyclerView.setAdapter(adapter);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String name = et_name.getText().toString();
                        String number = et_number.getText().toString();
                        ContactModel contactModel = new ContactModel(R.drawable.pic_a, name, number);
                        arrayContact.add(contactModel);
                        adapter.notifyItemInserted(arrayContact.size() - 1);

                        dialog.dismiss();

                    }
                });

                dialog.show();

            }
        });


    }
}