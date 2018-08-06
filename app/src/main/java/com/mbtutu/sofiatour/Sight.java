package com.mbtutu.sofiatour;

import com.google.firebase.firestore.GeoPoint;

public class Sight {
    private String name, descritpion, pictureUrl;
    private double price;
    private GeoPoint coordinates;

    Sight(String name, String description, String pictureUrl, double price, GeoPoint coordinates){
        this.name = name;
        this.descritpion = description;
        this.pictureUrl = pictureUrl;
        this.price = price;
        this.coordinates = coordinates;
    }

    Sight(){

    }



    String getName(){
        return name;
    }
    void setName(String name){
        this.name = name;
    }


    String getDescription(){
        return descritpion;
    }
    void setDescription(String descritpion){
        this.descritpion = descritpion;
    }

    String getPictureUrl(){
        return pictureUrl;
    }
    void setPictureurl(String pictureUrl){
        this.pictureUrl = pictureUrl;
    }


    double getPrice(){
        return price;
    }
    void setPrice(double price){
        this.price = price;
    }


    GeoPoint getCoordinates(){
        return coordinates;
    }
    void setCoordinates(GeoPoint coordinates){
        this.coordinates = coordinates;
    }




}
