package com.stepintoit.vkoth.calculatorapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stepintoit.vkoth.calculatorapplication.data.MySharedPreference;
import com.stepintoit.vkoth.calculatorapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculatorActivity extends AppCompatActivity {

    public static final int ADDITION = 1;
    public static final int MINUS = 2;
    public static final int MULTIPLY = 3;
    public static final int DIVISION = 4;

    private static double mValue1, mValue2;
    private static double mResult = 0;


    @BindView(R.id.edt_result)
    EditText edtResult;
    @BindView(R.id.edt_value1)
    EditText edtValue1;
    @BindView(R.id.edt_value2)
    EditText edtValue2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        ButterKnife.bind(this);


        String valueX = MySharedPreference.getInstance(CalculatorActivity.this).getValue(MySharedPreference.KEY_X);
        String valueY = MySharedPreference.getInstance(CalculatorActivity.this).getValue(MySharedPreference.KEY_Y);

        edtValue1.setText(valueX);
        edtValue2.setText(valueY);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        MySharedPreference.getInstance(CalculatorActivity.this).putValue(MySharedPreference.KEY_X, edtValue1.getText().toString());
        MySharedPreference.getInstance(CalculatorActivity.this).putValue(MySharedPreference.KEY_Y, edtValue2.getText().toString());

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itm_logout:


                MySharedPreference.getInstance(CalculatorActivity.this).deleteValue(MySharedPreference.KEY_TOKEN);
                //MySharedPreference.getInstance(CalculatorActivity.this).deleteValue(MySharedPreference.KEY_PASSWORD);
                startActivity(new Intent(CalculatorActivity.this, LoginActivity.class));
                finish();
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }

    @OnClick(R.id.btn_plus)
    void plus() {
        getResult(1);
    }
    @OnClick(R.id.btn_minus)
    void minus() {
        getResult(2);
    }
    @OnClick(R.id.btn_multiply)
    void multiply() {
        getResult(3);
    }
    @OnClick(R.id.btn_division)
    void division() {
        getResult(4);
    }

    public void getResult(int operation) {


        if ((edtValue1.getText().toString()).isEmpty() && (edtValue2.getText().toString()).isEmpty()) {
            Toast.makeText(getApplicationContext(), "Value of x and y can not be empty", Toast.LENGTH_SHORT).show();

        } else if ((edtValue1.getText().toString()).isEmpty()) {
            Toast.makeText(getApplicationContext(), "Value of x can not be empty", Toast.LENGTH_SHORT).show();

        } else if ((edtValue2.getText().toString()).isEmpty()) {
            Toast.makeText(getApplicationContext(), "Value of y can not be empty", Toast.LENGTH_SHORT).show();

        } else {

            mValue1 = Double.parseDouble(edtValue1.getText().toString());
            mValue2 = Double.parseDouble(edtValue2.getText().toString());

            if (operation == ADDITION)
                mResult = mValue1 + mValue2;
            else if (operation == MINUS)
                mResult = mValue1 - mValue2;
            else if (operation == MULTIPLY)
                mResult = mValue1 * mValue2;
            else if (operation == DIVISION)
                mResult = mValue1 / mValue2;

            edtResult.setText(Double.toString(mResult));
        }
    }
}
