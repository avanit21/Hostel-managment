package com.e.hob.DataModel;

public class datamodel_hostellist {

    private String hostelname, city, address , nearbycollage;
    private int rating , foodrating;

    public String getName() {
        return hostelname;
    }

    public void setName(String name) {
        this.hostelname = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNearbycollage() {
        return nearbycollage;
    }

    public void setNearbycollage(String nearbycollage) {
        this.nearbycollage = nearbycollage;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getFoodrating() {
        return foodrating;
    }

    public void setFoodrating(int foodrating) {
        this.foodrating = foodrating;
    }
}
