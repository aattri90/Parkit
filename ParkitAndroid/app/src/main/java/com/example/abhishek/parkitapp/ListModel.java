package com.example.abhishek.parkitapp;

public class ListModel {
    private String p_id;
    private String p_name;
    private String p_address;
    private String p_abbr;
    private String p_lat;
    private String p_long;
    private String reservation_fees;

    public  ListModel(String p_id, String p_name, String p_address, String p_abbr, String p_lat, String p_long, String reservation_fees)
    {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_address = p_address;
        this.p_abbr = p_abbr;
        this.p_lat = p_lat;
        this.p_long = p_long;
        this.reservation_fees = reservation_fees;
    }

    public String getP_id() {
        return p_id;
    }
    public String getP_name() {
        return p_name;
    }
    public String getP_address() {
        return p_address;
    }
    public String getP_abbr() {
        return p_abbr;
    }
    public String getP_lat() {
        return p_lat;
    }
    public String getP_long() {
        return p_long;
    }
    public String getReservation_fees() {
        return reservation_fees;
    }
}
