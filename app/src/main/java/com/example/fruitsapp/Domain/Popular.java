package com.example.fruitsapp.Domain;

import java.io.Serializable;

public class Popular implements Serializable {
   private   String title;
   private  String pic;
   private String description;
   private double fee;
   private int numberIncart;

    public Popular(String title, String pic, String description, double fee) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
    }

    public Popular(String title, String pic, String description, double fee, int numberIncart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.fee = fee;
        this.numberIncart = numberIncart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getNumberIncart() {
        return numberIncart;
    }

    public void setNumberIncart(int numberIncart) {
        this.numberIncart = numberIncart;
    }
}
