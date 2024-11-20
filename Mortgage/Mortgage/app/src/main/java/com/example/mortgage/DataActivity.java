package com.example.mortgage;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DataActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_data);
        updateView();
    }
    public void updateView(){

        Mortgage mortgage = MainActivity.mortgage;
        if (mortgage.getYears()==10){
            RadioButton rb10 = (RadioButton) findViewById(R.id.ten);
            rb10.setChecked(true);
        } else if (mortgage.getYears()==15) {
            RadioButton rb15 = (RadioButton) findViewById(R.id.fifteen);
            rb15.setChecked(true);
        }
        EditText amountET = (EditText)  findViewById(R.id.data_amount);
        amountET.setText(""+mortgage.getAmount());
        EditText rateET = (EditText) findViewById(R.id.data_rate);
        rateET.setText(""+mortgage.getRate());

    }
    public void updateMortgage(){
        Mortgage mortgage = MainActivity.mortgage;
        EditText amountET = (EditText) findViewById(R.id.data_amount);
        String amountStr = amountET.getText().toString();
        EditText rateET = (EditText) findViewById(R.id.data_rate);
        String rateStr = rateET.getText().toString();
        RadioButton rb10 = (RadioButton) findViewById(R.id.ten);
        RadioButton rb15 = (RadioButton) findViewById(R.id.fifteen);
        int years = 30;
        if (rb10.isChecked())
            years = 10;
        else if (rb15.isChecked()) {
            years = 15;
        }
        mortgage.setYears(years);
        try{
            float amount = Float.parseFloat(amountStr);
            mortgage.setAmount(amount);
            float rate = Float.parseFloat(rateStr);
            mortgage.setRate(rate);
        }
        catch (NumberFormatException e){
            mortgage.setAmount(100000.0f);
            mortgage.setRate(0.035f);
        }

    }
    public void goBack(View v){
        updateMortgage();
        this.finish();
        overridePendingTransition(R.anim.side_from_left, 0);
    }
}