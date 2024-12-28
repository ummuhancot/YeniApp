package com.tpe.domain;

public class User extends Address {

    private Integer userId;
    private String userName;
    private String userLastname;
    private String userEmail;
    private String userPassword;
    private String usertelefonNo;

    private Address adres;
    private Sepet sepet;

    private KullaniciOdakliAlanlar kullanıcıOdaklıAlanlar;

    public KullaniciOdakliAlanlar getKullanıcıOdaklıAlanlar() {
        return kullanıcıOdaklıAlanlar;
    }

    public void setKullanıcıOdaklıAlanlar(KullaniciOdakliAlanlar kullanıcıOdaklıAlanlar) {
        this.kullanıcıOdaklıAlanlar = kullanıcıOdaklıAlanlar;
    }

    public User() {
    }

    public User(Integer userId, String userName, String userLastname, String userEmail, String userPassword, String usertelefonNo, Address adres) {
        this.userId = userId;
        this.userName = userName;
        this.userLastname = userLastname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.usertelefonNo = usertelefonNo;
        this.adres = adres;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUsertelefonNo() {
        return usertelefonNo;
    }

    public void setUsertelefonNo(String usertelefonNo) {
        this.usertelefonNo = usertelefonNo;
    }

    public Address getAdres() {
        return adres;
    }

    public void setAdres(Address adres) {
        this.adres = adres;
    }

    public Sepet getSepet() {
        return sepet;
    }

    public void setSepet(Sepet sepet) {
        this.sepet = sepet;
    }
}