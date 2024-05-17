package com.example.styletheme;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder> {
    Context context;
    ArrayList<ContactModel> arrayContacts;

    public RecyclerContactAdapter(Context context, ArrayList<ContactModel> arrayContacts) {
        this.context = context;
        this.arrayContacts = arrayContacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.contactImage.setImageResource(arrayContacts.get(position).image);
        holder.contactName.setText(arrayContacts.get(position).contactName);
        holder.contactNumber.setText(arrayContacts.get(position).contactNumber);

        holder.llRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(context);

                dialog.setContentView(R.layout.add_update_layout);

                dialog.getWindow().setLayout(
                        RecyclerView.LayoutParams.MATCH_PARENT,
                        RecyclerView.LayoutParams.WRAP_CONTENT

                );

                EditText et_name = dialog.findViewById(R.id.et_contactName);
                EditText et_number = dialog.findViewById(R.id.et_contactNumber);
                Button btnAction = dialog.findViewById(R.id.savebtn);
                btnAction.setText("Update");
                TextView txtTitle = dialog.findViewById(R.id.titleId);
                txtTitle.setText("Update Contact");

                et_name.setText(arrayContacts.get(position).contactName);
                et_number.setText(arrayContacts.get(position).contactNumber);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String name = et_name.getText().toString();
                        String number = et_number.getText().toString();
                        ContactModel contactModel = new ContactModel(R.drawable.pic_a, name, number);
                        arrayContacts.set(position, contactModel);

                        notifyItemChanged(position);

                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });

        holder.llRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure to delete contact?")
                        .setIcon(R.drawable.pic_a)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayContacts.remove(position);
                                notifyItemRemoved(position);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(context, "Cancel Delete", Toast.LENGTH_SHORT).show();
                                notify();
                            }
                        });
                builder.show();

                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView contactName, contactNumber;
        ImageView contactImage;
        LinearLayout llRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            contactName = itemView.findViewById(R.id.contactName);
            contactNumber = itemView.findViewById(R.id.contactNumber);
            contactImage = itemView.findViewById(R.id.imgContact);

            llRow = itemView.findViewById(R.id.llRow);
        }
    }


}
