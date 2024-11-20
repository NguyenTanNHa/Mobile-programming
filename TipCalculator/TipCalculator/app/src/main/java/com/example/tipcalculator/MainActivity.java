package com.example.tipcalculator;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    TipCalculator tipCalculator;

    NumberFormat money = NumberFormat.getCurrencyInstance();
    private EditText billET;
    private EditText tipET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tipCalculator = new TipCalculator(0.15f, 100.0f);

        setContentView(R.layout.activity_main);
        billET = (EditText) findViewById(R.id.amount_bill);
        tipET = (EditText) findViewById(R.id.amount_tip_percent);

        TextChangeHandler textChangeHandler = new TextChangeHandler();
        billET.addTextChangedListener(textChangeHandler);
        tipET.addTextChangedListener(textChangeHandler);


    }

    public void calculate(View view){
        Log.w("MainActivity", "v = " + view);
        EditText billEditText = (EditText) findViewById(R.id.amount_bill);
        EditText tipEditText = (EditText) findViewById(R.id.amount_tip_percent);
        TextView tipTextView = (TextView) findViewById(R.id.amount_tip);
        TextView totalTextView = (TextView) findViewById(R.id.amount_total);

        String billString = billEditText.getText().toString();
        String tipString = tipEditText.getText().toString();
        try {
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);
            tipCalculator.setBill(billAmount);
            tipCalculator.setTip(tipPercent*0.01f);
            float tip = tipCalculator.tipAmount();
            float total = tipCalculator.totalAmount();

            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));
        } catch (NumberFormatException e){

        }
    }
    public void calculate(){
        String billString = billET.getText().toString();
        String tipString = tipET.getText().toString();

        TextView tipTextView = (TextView) findViewById(R.id.amount_tip);
        TextView totalTextView = (TextView) findViewById(R.id.amount_total);

        try {
            float billAmount = Float.parseFloat(billString);
            int tipPercent = Integer.parseInt(tipString);

            tipCalculator.setBill(billAmount);
            tipCalculator.setTip(tipPercent*0.01f);

            float tip = tipCalculator.tipAmount();
            float total = tipCalculator.totalAmount();

            tipTextView.setText(money.format(tip));
            totalTextView.setText(money.format(total));

        }

        catch (Exception e){

        }
    }

    public void tipPercentCondition(){

    }
    private class TextChangeHandler implements TextWatcher {
        @Override
        public void afterTextChanged(Editable s) {
            calculate();
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
    }
}
