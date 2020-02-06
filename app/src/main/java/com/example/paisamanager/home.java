package com.example.paisamanager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class home extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private ArrayList<Users> lis ;
    private TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        lis = FileHelper.readData(this);
        final TextView name = (TextView) findViewById(R.id.name);
        final TextView amount = (TextView) findViewById(R.id.amount);
        final TextView desc = (TextView) findViewById(R.id.desc);
        date = (TextView) findViewById(R.id.textView);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
        Button add_new = (Button) findViewById(R.id.add_new);
        add_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (name.getText().toString().isEmpty())
                        name.setError("Please Enter Name");
                    else if (amount.getText().toString().isEmpty())
                        amount.setError("Please Enter Amount");
                    else if (date.getText().equals("DD/MMM/YYYY"))
                        date.setError("Please Enter Date");
                    else if (desc.getText().toString().isEmpty())
                        desc.setError("Please Fill description");
                    else
                        saveNew(name.getText().toString(), amount.getText().toString(), desc.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        Button view_List = (Button) findViewById(R.id.viewlist);
        view_List.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                foo();
            }
        });
    }

    private void saveNew(String name, String amount, String desc) throws ParseException {
        Users users = new Users();
        users.setName(name);
        users.setAmount(Integer.parseInt(amount));
        Date newDate = new SimpleDateFormat("d/MMM/yyyy").parse(date.getText().toString());
        users.setTransDate(newDate);
        users.setStatus(Boolean.FALSE);
        users.setUpdatedat(LocalDateTime.now());
        users.setDescription("Description : " + desc);
        lis.add(users);
        Toast toast = Toast.makeText(this, "New Transaction Added Succefully", Toast.LENGTH_LONG);
        toast.show();
        FileHelper.writeData(lis,this );

    }

    private void foo() {
        Intent intent = new Intent(this, Records.class);
        intent.putExtra("data", lis);
        startActivity(intent);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("d/MMM/yyyy");

        date.setText(simpleDateFormat.format(calendar.getTime()));
    }
}
