package com.example.mortgage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static Mortgage mortgage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mortgage = new Mortgage();
    }
    public void onStart(){
        super.onStart();
        updateView();
    }
    public void updateView(){
        TextView amountTV = (TextView) findViewById(R.id.amount);
        amountTV.setText(""+mortgage.getFormatAmount());
        TextView yearsTV = (TextView)  findViewById(R.id.years);
        yearsTV.setText(""+mortgage.getYears());
        TextView rateTV = (TextView) findViewById(R.id.rate);
        rateTV.setText(100*mortgage.getRate() + "%");
        TextView monthlyTV = (TextView) findViewById(R.id.monthly_payment);
        monthlyTV.setText(mortgage.formatMonthlyPayment());
        TextView totalTV = (TextView) findViewById(R.id.total_payment);
        totalTV.setText(mortgage.formatTotalPayment());
    }
    public void modifyData(View v){
        Intent intent = new Intent(this, DataActivity.class);
        this.startActivity(intent);
        overridePendingTransition(R.anim.side_from_left, 0);
    }
}
