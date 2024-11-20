package com.example.mortgage;

import java.text.DecimalFormat;

public class Mortgage {
    public final DecimalFormat MONEY = new DecimalFormat("$#,##0.00");
    private float amount;
    private int years;
    private float rate;
    public Mortgage(){
        amount = 100000.0f;
        years = 30;
        rate = 0.035f;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getAmount() {
        return amount;
    }

    public int getYears() {
        return years;
    }

    public float getRate() {
        return rate;
    }
    public String getFormatAmount(){
        return MONEY.format(amount);
    }
    public float monthlyPayment(){
        float mRate = rate/12;
        double temp = Math.pow(1/(1+mRate), years*12);
        return amount*mRate/(float) (1-temp);
    }
    public String formatMonthlyPayment(){
        return MONEY.format(monthlyPayment());
    }
    public float totalPayment(){
        return monthlyPayment()*years*12;
    }
    public String formatTotalPayment(){
        return MONEY.format(totalPayment());
    }
}
