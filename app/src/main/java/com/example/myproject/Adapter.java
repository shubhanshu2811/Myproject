package com.example.myproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.HashMap;

import static com.example.myproject.helper.hashMaplist;


public class Adapter extends RecyclerView.Adapter<Adapter.Dashboard_ViewHolder> {



    Context context;
    String[] arrays;
    String answer;
    String question;

    public Adapter(Context context, String[] arrays, String answer, String s) {
        this.context = context;
        this.arrays = arrays;
        this.answer=answer;
        this.question=s;




    }

    @NonNull
    @Override
    public Dashboard_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_main, parent, false);
        Dashboard_ViewHolder dashboard_viewHolder = new Dashboard_ViewHolder(view);
        return dashboard_viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final Dashboard_ViewHolder holder, final int position) {

        holder.text.setText(arrays[position]);
        if (hashMaplist.containsValue(arrays[position])){
            holder.checkbox.setChecked(true);
        }


        /*holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    if (arrays[position].equals(answer)) {

                        Toast.makeText(context,"correct ans",Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(context,"Wrong ans",Toast.LENGTH_LONG).show();

                    }
                }
                else
                {
                    // not checked
                }
            }

        });*/

        holder.rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrays[position].equals(answer)) {
                    holder.checkbox.setChecked(true);
                    hashMaplist.put(question,answer);


                    Toast.makeText(context,"correct ans",Toast.LENGTH_LONG).show();
                }
                else {
                
                    holder.checkbox.setChecked(false);
                    Toast.makeText(context,"Wrong ans",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrays.length;
    }

    public class Dashboard_ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        LinearLayout rel;
        CheckBox checkbox;


        public Dashboard_ViewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.text);
            rel=itemView.findViewById(R.id.rel);
            checkbox=itemView.findViewById(R.id.checkbox);



        }
    }
}


