package com.example.paisamanager;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.pviewholder> {

    private ArrayList<Users> data;
    private Context context;
    public MyAdapter(Context context, ArrayList<Users> data){
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public pviewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_data, viewGroup,false);
        return new pviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final pviewholder pviewholder, int i) {
        pviewholder.name.setText(data.get(i).getName());
        pviewholder.amount.setText(String.format(data.get(i).getAmount().toString()));
        pviewholder.date.setText(data.get(i).getUpdatedat().format(DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm")));
        if (data.get(i).getStatus()){
            pviewholder.status.setText("SETTLED");
            pviewholder.status.setTextColor(Color.GREEN);
        }
        else {
            pviewholder.status.setText("OWNED");
            pviewholder.status.setTextColor(Color.RED);
        }
        pviewholder.desc.setText(data.get(0).getDescription());

        final Dialog mydialog = new Dialog(context);
        mydialog.setContentView(R.layout.layout_);


        pviewholder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView name = (TextView) mydialog.findViewById(R.id.name);
                final TextView amount = (TextView) mydialog.findViewById(R.id.amount);
                Button change = (Button) mydialog.findViewById(R.id.change);
                Button settle = (Button) mydialog.findViewById(R.id.settle);
                name.setText(data.get(pviewholder.getAdapterPosition()).getName());
                amount.setText(String.format(data.get(pviewholder.getAdapterPosition()).getAmount().toString()));
                mydialog.show();

                settle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        data.get(pviewholder.getAdapterPosition()).setStatus(Boolean.TRUE);
                        FileHelper.writeData(data,context );
                        Intent intent = new Intent(context, Records.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("data", data);
                        context.startActivity(intent);
                    }
                });
                change.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        data.get(pviewholder.getAdapterPosition()).setAmount(
                                Integer.parseInt(amount.getText().toString()));
                        FileHelper.writeData(data,context );
                        Intent intent = new Intent(context, Records.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("data", data);
                        context.startActivity(intent);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class pviewholder extends RecyclerView.ViewHolder{
        TextView name;
        TextView status;
        TextView amount;
        TextView date;
        TextView desc;
        View view;

        public pviewholder(View itenv){
            super(itenv);
            name = (TextView) itenv.findViewById(R.id.name);
            amount = (TextView) itenv.findViewById(R.id.amount);
            status = (TextView) itenv.findViewById(R.id.status);
            date = (TextView) itenv.findViewById(R.id.date);
            desc = (TextView) itenv.findViewById(R.id.desc);
            view = itenv;
        }
    }
}
