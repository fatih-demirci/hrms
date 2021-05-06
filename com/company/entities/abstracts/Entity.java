package com.company.entities.abstracts;

public interface Entity {
    public String getName();
    public void setName(String name);
    public double getPrice();
    public void setPrice(double price);
    public double getPercentageOfDiscount();
    public void setPercentageOfDiscount(double percentageOfDiscount);
    public void removeDiscount();
}
