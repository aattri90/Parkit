package com.example.abhishek.parkitapp;

public class TransactionModel {
    private String parkingName;
    private String vehicleNo;
    private String date;
    private String bill;

    public TransactionModel(String parkingName,String vehicleNo,String date,String bill)
    {
        this.parkingName = parkingName;
        this.vehicleNo = vehicleNo;
        this.date = date;
        this.bill = bill;
    }

    public String getBill() {
        return bill;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getDate() {
        return date;
    }

    public String getParkingName() {
        return parkingName;
    }
}
