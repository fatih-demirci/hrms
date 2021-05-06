package com.company.entities.concretes;

import com.company.entities.abstracts.Entity;

public class Game implements Entity {
    private int id;
    private String name;
    private double price;




    private double percentageOfDiscount;

    public Game(int id,String name, double price) {
        this.id=id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        if(percentageOfDiscount!=0){
            return price*(100-percentageOfDiscount)/100;
        }
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPercentageOfDiscount() {
        return percentageOfDiscount;
    }

    public void setPercentageOfDiscount(double percentageOfDiscount) {
        this.percentageOfDiscount = percentageOfDiscount;
    }

    public void removeDiscount(){
        percentageOfDiscount=0;
    }
}
