package com.tpe.domain;
/*### Kullanıcı Odaklı Alanlar:
23. **Değerlendirme (Rating)**: Kullanıcıların verdiği yıldız puanı.
24. **Yorumlar (Reviews)**: Kullanıcı yorumları.*/
public class KullaniciOdakliAlanlar {

    private int degerlendirme;
    private String yorumlar;

    public KullaniciOdakliAlanlar() {
    }

    public KullaniciOdakliAlanlar(int degerlendirme, String yorumlar) {
        this.degerlendirme = degerlendirme;
        this.yorumlar = yorumlar;
    }

    public int getDegerlendirme() {
        return degerlendirme;
    }

    public void setDegerlendirme(int degerlendirme) {
        this.degerlendirme = degerlendirme;
    }

    public String getYorumlar() {
        return yorumlar;
    }

    public void setYorumlar(String yorumlar) {
        this.yorumlar = yorumlar;
    }

    @Override
    public String toString() {
        return "KullanıcıOdaklıAlanlar{" +
                "degerlendirme=" + degerlendirme +
                ", yorumlar='" + yorumlar + '\'' +
                '}';
    }
}
