package com.aleks.planner;

import java.io.Serializable;

public class Workday implements Serializable {
    private String date;
    private double hours;
    private double money;
    private double total;

    public Workday(String date, double hours, double money){
        this.date = date;
        this.hours = hours;
        this.money = money;
    }
    public Workday(){

    }
    public String getDate(){
        return date;
    }
    public double getHours(){
        return hours;
    }
    public double getMoney(){
        return money;
    }

    public double getTotal() {
        return (money * hours);
    }
    public void setDate(String date){
        this.date = date;
    }
    public void setHours(double hours){
        this.hours = hours;
    }
    public void setMoney(double money){
        this.money = money;
    }
}
