package com.mbtutu.sofiatour;

import java.util.ArrayList;

public class TourBundle {

        private String name;
    private String descritpion;
    private String pictureUrl;
        private double price;
        private ArrayList<String> tours;

        TourBundle(String name, String description, String pictureUrl, double price, ArrayList<String> tours){
            this.setName(name);
            this.setDescritpion(description);
            this.setPictureUrl(pictureUrl);
            this.setPrice(price);
            this.setTours(tours);
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

    public ArrayList<String> getTours() {
        return tours;
    }

    public void setTours(ArrayList<String> tours) {
        this.tours = tours;
    }
}


