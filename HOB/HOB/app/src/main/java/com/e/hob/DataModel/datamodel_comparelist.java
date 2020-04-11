package com.e.hob.DataModel;

public class datamodel_comparelist {

    private String hostelname, nearbycollage ;
    private int rating , foodrating;

    public String getName() {
        return hostelname;
    }

    public void setName(String name) {
        this.hostelname = name;
    }

    public String getNearbycollage() {
        return nearbycollage;
    }

    public void setNearbycollage(String nearbycollage) {
        this.nearbycollage = nearbycollage;
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
