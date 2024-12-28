package com.tpe.domain;

import java.util.List;

/*Sepet İşlemleri:
Sepete Eklenen Miktar (Cart Quantity): Kullanıcının sepete eklediği ürün miktarı.

Örn: 2 adet "Klasik Beyaz Gömlek".
Sepet ID (Cart ID): Sepeti benzersiz şekilde tanımlayan ID.

Örn: Kullanıcının farklı sepet oturumlarını izlemek için kullanılabilir.*/
public class Sepet <T>{

    //sepet
    private static List<Urun> products;
    public Urun urun;
    private static int miktar;
    //satış
    private String tarih;
    private String satisDurumu;
    private String Tamamlandı;
    private String krediCart;
    private String kapıdaOdeme;
    private String faturaBilgileri;

    //kargo
    private String kargoTakipNo;
    private String teslimatSüresi;
    private String kargoUcreti;
    private String yurtici;
    private String yurtdısı;
    private String kargoDurumu;

    public Sepet(Urun urun, int miktar) {
    }

    public Urun getUrun() {
        return urun;
    }

    public void setUrun(Urun urun) {
        this.urun = urun;
    }

    public static int getMiktar() {
        return miktar;
    }

    public static void setMiktar(int miktar) {
        Sepet.miktar = miktar;
    }

    public Sepet() {
    }

    public Sepet(String ürünAdı, String kategori, Integer fiyat, String beden, String renk, String malzeme, String kolTipi, Integer boyUzunlugu, int stokDurumu, String uretici, String tarih, String satisDurumu, String tamamlandı, String krediCart, String kapıdaOdeme, String faturaBilgileri, String kargoTakipNo, String teslimatSüresi, String kargoUcreti, String yurtici, String yurtdısı, String kargoDurumu) {

        this.tarih = tarih;
        this.satisDurumu = satisDurumu;
        this.Tamamlandı = tamamlandı;
        this.krediCart = krediCart;
        this.kapıdaOdeme = kapıdaOdeme;
        this.faturaBilgileri = faturaBilgileri;
        this.kargoTakipNo = kargoTakipNo;
        this.teslimatSüresi = teslimatSüresi;
        this.kargoUcreti = kargoUcreti;
        this.yurtici = yurtici;
        this.yurtdısı = yurtdısı;
        this.kargoDurumu = kargoDurumu;
    }

    public Sepet(List<Sepet> sepetler, Urun urun, int miktar) {
    }


    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getSatisDurumu() {
        return satisDurumu;
    }

    public void setSatisDurumu(String satisDurumu) {
        this.satisDurumu = satisDurumu;
    }

    public String getTamamlandı() {
        return Tamamlandı;
    }

    public void setTamamlandı(String tamamlandı) {
        Tamamlandı = tamamlandı;
    }

    public String getKrediCart() {
        return krediCart;
    }

    public void setKrediCart(String krediCart) {
        this.krediCart = krediCart;
    }

    public String getKapıdaOdeme() {
        return kapıdaOdeme;
    }

    public void setKapıdaOdeme(String kapıdaOdeme) {
        this.kapıdaOdeme = kapıdaOdeme;
    }

    public String getFaturaBilgileri() {
        return faturaBilgileri;
    }

    public void setFaturaBilgileri(String faturaBilgileri) {
        this.faturaBilgileri = faturaBilgileri;
    }

    public String getKargoTakipNo() {
        return kargoTakipNo;
    }

    public void setKargoTakipNo(String kargoTakipNo) {
        this.kargoTakipNo = kargoTakipNo;
    }

    public String getTeslimatSüresi() {
        return teslimatSüresi;
    }

    public void setTeslimatSüresi(String teslimatSüresi) {
        this.teslimatSüresi = teslimatSüresi;
    }

    public String getKargoUcreti() {
        return kargoUcreti;
    }

    public void setKargoUcreti(String kargoUcreti) {
        this.kargoUcreti = kargoUcreti;
    }

    public String getYurtici() {
        return yurtici;
    }

    public void setYurtici(String yurtici) {
        this.yurtici = yurtici;
    }

    public String getYurtdısı() {
        return yurtdısı;
    }

    public void setYurtdısı(String yurtdısı) {
        this.yurtdısı = yurtdısı;
    }

    public String getKargoDurumu() {
        return kargoDurumu;
    }

    public void setKargoDurumu(String kargoDurumu) {
        this.kargoDurumu = kargoDurumu;
    }

    @Override
    public String toString() {
        return "Sepet{" +
                ", products=" + products +
                ", tarih='" + tarih + '\'' +
                ", satisDurumu='" + satisDurumu + '\'' +
                ", Tamamlandı='" + Tamamlandı + '\'' +
                ", krediCart='" + krediCart + '\'' +
                ", kapıdaOdeme='" + kapıdaOdeme + '\'' +
                ", faturaBilgileri='" + faturaBilgileri + '\'' +
                ", kargoTakipNo='" + kargoTakipNo + '\'' +
                ", teslimatSüresi='" + teslimatSüresi + '\'' +
                ", kargoUcreti='" + kargoUcreti + '\'' +
                ", yurtici='" + yurtici + '\'' +
                ", yurtdısı='" + yurtdısı + '\'' +
                ", kargoDurumu='" + kargoDurumu + '\'' +
                '}';
    }
}
