package com.stepintoit.vkoth.calculatorapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {

    Button plusB,minusB,divisionB,multiplyB;
    EditText resultEdittext;

    EditText v1,v2;

    int x,y,ans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        plusB = (Button)findViewById(R.id.plusButton);
        minusB = (Button)findViewById(R.id.minusButton);
        multiplyB = (Button)findViewById(R.id.multiplyButton);
        divisionB = (Button)findViewById(R.id.divisionButton);

        v1 = (EditText)findViewById(R.id.value1) ;
        v2= (EditText)findViewById(R.id.value2);

        resultEdittext = (EditText)findViewById(R.id.editText_result);


       plusB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                x = Integer.parseInt(v1.getText().toString());
                y = Integer.parseInt(v2.getText().toString());
                ans = x + y;
                resultEdittext.setText(Integer.toString(ans));
            }
        });

        minusB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                x = Integer.parseInt(v1.getText().toString());
                y = Integer.parseInt(v2.getText().toString());
                ans = x - y;
                resultEdittext.setText(Integer.toString(ans));
            }
        });

        multiplyB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                x = Integer.parseInt(v1.getText().toString());
                y = Integer.parseInt(v2.getText().toString());
                ans = x * y;
                resultEdittext.setText(Integer.toString(ans));
            }
        });
        divisionB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                x = Integer.parseInt(v1.getText().toString());
                y = Integer.parseInt(v2.getText().toString());
                ans = x / y;
                resultEdittext.setText(Integer.toString(ans));
            }
        });
    }
}
