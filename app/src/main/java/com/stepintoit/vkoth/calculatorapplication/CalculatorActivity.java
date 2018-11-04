package com.stepintoit.vkoth.calculatorapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CalculatorActivity extends AppCompatActivity {

    Button btnPlus, btnMinus, btnDivision, btnMultiply;
    EditText edtResult;

    EditText edtValue1, edtValue2;

    int mValue1, mValue2, mResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        btnPlus = (Button)findViewById(R.id.btn_plus);
        btnMinus = (Button)findViewById(R.id.btn_minus);
        btnMultiply = (Button)findViewById(R.id.btn_multiply);
        btnDivision = (Button)findViewById(R.id.btn_division);

        edtValue1 = (EditText)findViewById(R.id.edt_value1) ;
        edtValue2 = (EditText)findViewById(R.id.edt_value2);

        edtResult = (EditText)findViewById(R.id.edt_result);


       btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mValue1 = Integer.parseInt(edtValue1.getText().toString());
                mValue2 = Integer.parseInt(edtValue2.getText().toString());
                mResult = mValue1 + mValue2;
                edtResult.setText(Integer.toString(mResult));
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mValue1 = Integer.parseInt(edtValue1.getText().toString());
                mValue2 = Integer.parseInt(edtValue2.getText().toString());
                mResult = mValue1 - mValue2;
                edtResult.setText(Integer.toString(mResult));
            }
        });

        btnMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mValue1 = Integer.parseInt(edtValue1.getText().toString());
                mValue2 = Integer.parseInt(edtValue2.getText().toString());
                mResult = mValue1 * mValue2;
                edtResult.setText(Integer.toString(mResult));
            }
        });
        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mValue1 = Integer.parseInt(edtValue1.getText().toString());
                mValue2 = Integer.parseInt(edtValue2.getText().toString());
                mResult = mValue1 / mValue2;
                edtResult.setText(Integer.toString(mResult));
            }
        });
    }
}
