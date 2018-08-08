package com.mbtutu.sofiatour;

import java.util.ArrayList;

public class TourBundle {

    private String name;
    private String descritpion;
    private String pictureUrl;
    private double price;
    private ArrayList<Sight> sights;

    TourBundle(String name, String description, String pictureUrl, double price, ArrayList<Sight> sights){
        this.setName(name);
        this.setDescritpion(description);
        this.setPictureUrl(pictureUrl);
        this.setPrice(price);
        this.setSights(sights);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList<Sight> getSights() {
        return sights;
    }

    public void setSights(ArrayList<Sight> sights) {
        this.sights = sights;
    }
}


