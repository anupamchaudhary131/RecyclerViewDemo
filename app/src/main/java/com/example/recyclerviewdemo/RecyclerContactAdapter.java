package com.example.recyclerviewdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerContactAdapter extends RecyclerView.Adapter<RecyclerContactAdapter.ViewHolder>{

    Context context;
    ArrayList<ContactModel> arrContacts;

    RecyclerContactAdapter(Context context, ArrayList<ContactModel> arrContacts){
        this.context = context;
        this.arrContacts = arrContacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.contact_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ContactModel model= (ContactModel)arrContacts.get(position);
        holder.imgContact.setImageResource(model.img);
        holder.txtName.setText(model.name);
        holder.txtNumber.setText(model.number);

        setAnimation(holder.itemView, position);

        holder.IIRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.custom_add_contact);


                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtContact = dialog.findViewById(R.id.edtContact);
                Button btnAction = dialog.findViewById(R.id.btnAdd);
                TextView txtTitle = dialog.findViewById(R.id.txtAddContacts);

                btnAction.setText("Update");
                txtTitle.setText("Update Contact");

                edtName.setText(arrContacts.get(position).name);
                edtContact.setText(arrContacts.get(position).number);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String name ="", number="";

                        if(!edtName.getText().toString().equals("")) {
                            name = edtName.getText().toString();
                        }
                        else{
                            Toast.makeText(context, "Please Enter Contact Name", Toast.LENGTH_LONG).show();
                        }

                        if(!edtContact.getText().toString().equals("")){
                            number = edtContact.getText().toString();
                        }
                        else{
                            Toast.makeText(context, "Please Enter Contact Number", Toast.LENGTH_LONG).show();
                        }

                        arrContacts.set(position, new ContactModel(R.drawable.avatar1, name, number));
                        notifyItemChanged(position);

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        holder.IIRow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete  Contact")
                        .setMessage("Are You Sure You Want To Delete ?")
                        .setIcon(R.drawable.ic_baseline_delete_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrContacts.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                builder.show();

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtNumber;
        ImageView imgContact;
        LinearLayout IIRow;

        public ViewHolder(View itemView)
        {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            imgContact = itemView.findViewById(R.id.imgContact);
            IIRow = itemView.findViewById(R.id.IIRow);
        }
    }


    private void setAnimation(View viewToAnimate, int position)
    {
        Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
        viewToAnimate.startAnimation(slideIn);
    }
}
