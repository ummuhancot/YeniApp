package com.tpe.domain;

import java.util.HashMap;
import java.util.Map;

public class Address extends KullaniciOdakliAlanlar{


    private String street;

    private String city;

    private String country;

    private String zipcode;

    public Map<String,String> userInfo = new HashMap<>(); //country , city

    public Address() {
    }

    public Address(String adress) {
    }

    public Address(Map<String, String> userInfo) {
        this.userInfo = userInfo;
    }

    public Address(String street, String city, String country, String zipcode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }


}
