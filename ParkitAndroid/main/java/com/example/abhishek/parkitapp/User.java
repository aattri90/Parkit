package com.example.abhishek.parkitapp;

public class User {
    private String name,license,street,city,state,country,zip,mobile,email,password;
    User(String name,String license,String street,String city,String state,String country,String zip,String mobile,String email,String password)
    {
        this.name = name;
        this.license = license;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zip = zip;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getLicense() {
        return license;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }
}
