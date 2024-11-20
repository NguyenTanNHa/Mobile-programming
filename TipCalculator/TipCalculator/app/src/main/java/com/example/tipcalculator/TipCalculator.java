package com.example.tipcalculator;

public class TipCalculator {
    private float tip;
    private float bill;

    public TipCalculator (float newTip, float newBill){
        setBill(newBill);
        setTip(newTip);
    }
    public float getBill(){
        return bill;
    }

    public float getTip() {
        return tip;
    }
    public void setBill(float bill) {
        if (bill>0){
            this.bill = bill;
        }

    }
    public void setTip(float tip) {
        if (tip>0){
            this.tip = tip;
        }
    }
    public float tipAmount(){
        return bill*tip;
    }
    public float totalAmount(){
        return bill+tipAmount();
    }
}
