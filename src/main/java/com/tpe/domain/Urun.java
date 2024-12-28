package com.tpe.domain;
/*
* Bir kıyafet ürünü için kullanabileceğiniz alanlar (fields) şunlardır:

### Temel Alanlar:
1. **Urun Adı (Product Name)**: Ürünün adı. Örn: "Klasik Beyaz Gömlek"
2. **Urun Kodu (Product Code)**: Stok takibi ve benzersizlik için özel kod. Örn: "BG-2024-001"
3. **Kategori (Category)**: Ürünün hangi kategoride yer aldığı. Örn: "Kadın Gömlek", "Erkek Pantolon".
4. **Fiyat (Price)**: Ürünün satış fiyatı. Örn: "499.99 TL"
5. **Para Birimi (Currency)**: Fiyatın hangi para biriminde olduğu. Örn: "TL", "USD".
*/
public class Urun extends Sepet{

    public static int counter=1;
    private String ÜrünAdı;
    private String ÜrünKodu;
    private String Kategori;
    private Integer Fiyat;


    private String beden;
    private String renk;
    private String malzeme;
    private String kolTipi;
    private Integer boyUzunlugu;

    private int stokDurumu;
    private String uretici;

    public Urun() {

    }

    public Urun(String ürünAdı, String kategori, Integer fiyat, String beden, String renk, String malzeme, String kolTipi, Integer boyUzunlugu, int stokDurumu, String uretici) {
        //super(ürünAdı, kategori, fiyat, beden, renk, malzeme, kolTipi, boyUzunlugu, stokDurumu, uretici);

        this.ÜrünAdı = ürünAdı;
        this.Kategori = kategori;
        this.Fiyat = fiyat;
        this.beden = beden;
        this.renk = renk;
        this.malzeme = malzeme;
        this.kolTipi = kolTipi;
        this.boyUzunlugu = boyUzunlugu;
        this.stokDurumu = stokDurumu;
        this.uretici = uretici;
        this.ÜrünKodu = "\uD83D\uDE08" + counter++;
    }

    public Urun(Urun product, int productQuantity) {
    }

    public String getÜrünAdı() {
        return ÜrünAdı;
    }

    public void setÜrünAdı(String ürünAdı) {
        ÜrünAdı = ürünAdı;
    }

    public String getÜrünKodu() {
        return ÜrünKodu;
    }

    public void setÜrünKodu(String ürünKodu) {
        ÜrünKodu = ürünKodu;
    }

    public String getKategori() {
        return Kategori;
    }

    public void setKategori(String kategori) {
        Kategori = kategori;
    }

    public Integer getFiyat() {
        return Fiyat;
    }

    public void setFiyat(Integer fiyat) {
        Fiyat = fiyat;
    }

    public String getBeden() {
        return beden;
    }

    public void setBeden(String beden) {
        this.beden = beden;
    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public String getMalzeme() {
        return malzeme;
    }

    public void setMalzeme(String malzeme) {
        this.malzeme = malzeme;
    }

    public String getKolTipi() {
        return kolTipi;
    }

    public void setKolTipi(String kolTipi) {
        this.kolTipi = kolTipi;
    }

    public Integer getBoyUzunlugu() {
        return boyUzunlugu;
    }

    public void setBoyUzunlugu(Integer boyUzunlugu) {
        this.boyUzunlugu = boyUzunlugu;
    }

    public int getStokDurumu() {
        return stokDurumu;
    }

    public void setStokDurumu(int stokDurumu) {
        this.stokDurumu = stokDurumu;
    }

    public String getUretici() {
        return uretici;
    }

    public void setUretici(String uretici) {
        this.uretici = uretici;
    }

    @Override
    public String toString() {
        return "Urun{" +
                "ÜrünAdı='" + ÜrünAdı + '\'' +
                ", ÜrünKodu='" + ÜrünKodu + '\'' +
                ", Kategori='" + Kategori + '\'' +
                ", Fiyat=" + Fiyat +
                ", beden='" + beden + '\'' +
                ", renk='" + renk + '\'' +
                ", malzeme='" + malzeme + '\'' +
                ", kolTipi='" + kolTipi + '\'' +
                ", boyUzunlugu='" + boyUzunlugu + '\'' +
                ", stokDurumu=" + stokDurumu +
                ", uretici='" + uretici + '\'' +
                '}';
    }
}
