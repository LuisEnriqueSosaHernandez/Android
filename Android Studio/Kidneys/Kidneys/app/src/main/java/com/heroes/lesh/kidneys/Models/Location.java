package com.heroes.lesh.kidneys.Models;


public class Location {

    private int idLocation;
    private String name;
    private double longitude;
    private double latitude;
    private String category;
    private String locality;
    private String addreess;


    public Location() {

    }

    public Location(int idLocation, String name, double longitude, double latitude, String category, String locality, String addreess) {
        this.idLocation = idLocation;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.category = category;
        this.locality = locality;
        this.addreess = addreess;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddreess() {
        return addreess;
    }

    public void setAddreess(String addreess) {
        this.addreess = addreess;
    }
}
