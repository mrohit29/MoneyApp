package com.example.paisamanager;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView pass =(TextView) findViewById(R.id.editText);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((Integer.parseInt(pass.getText().toString())) == 1509)
                    fun();
                else
                    foo();
            }
        });
    }

    private void foo() {
        Toast toast = Toast.makeText(this, "galat ho tum", Toast.LENGTH_LONG);
        toast.show();
    }

    private void fun() {
        Intent intent = new Intent(this, home.class);
        startActivity(intent);
    }
}
