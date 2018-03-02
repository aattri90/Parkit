package com.example.abhishek.parkitapp;


public class NavigationModel {
    private String parkingName;
    private String date;
    private String latitude;
    private String longitude;

    public NavigationModel(String parkingName,String date,String latitude,String longitude)
    {
        this.parkingName = parkingName;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getParkingName() {
        return parkingName;
    }

    public String getDate() {
        return date;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
