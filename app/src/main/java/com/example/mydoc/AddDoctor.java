package com.example.mydoc;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;

public class AddDoctor extends AppCompatActivity {
    EditText name=findViewById(R.id.Doctorid),spec=findViewById(R.id.Specilation),phone=findViewById(R.id.contactPhone);
    RadioGroup r=findViewById(R.id.grp);
    NumberPicker num=findViewById(R.id.num);
    Button sub=findViewById(R.id.accept),ret=findViewById(R.id.returna);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.add_doctor);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
