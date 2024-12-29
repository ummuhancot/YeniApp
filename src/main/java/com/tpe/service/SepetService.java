package com.tpe.service;

import com.tpe.domain.Sepet;
import com.tpe.domain.Urun;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class SepetService {

    private static Scanner sc = new Scanner(System.in);

    // Statik Listeler
    public static List<Urun> sepetList = new ArrayList<>();
    public static List<Urun> urunList = new ArrayList<>();


    // Sepete Ürün Ekleme
    public static List<Urun> addToCart(Map<String, Urun> producta, List<Urun> cart) {
        int select;
        List<Urun>products=new ArrayList<>();
        products.addAll(producta.values().stream().toList());

        do {
            boolean hasUnaddedProducts = products.stream()
                    .anyMatch(product -> product.getStokDurumu() > 0 && cart.stream().noneMatch(item -> item.getUrun().equals(product)));

            if (hasUnaddedProducts) {
                System.out.println("Stokta olup sepete eklenmemiş ürünler:");
                listUnaddedProducts(products, cart);
            } else {
                System.out.println("Tüm ürünler sepette. Sepeti güncellemek için tüm ürünler listeleniyor:");
                listCart(products);
            }

            System.out.print("Sepete eklemek/güncellemek istediğiniz ürünün ID'sini giriniz: ");
            String productId = sc.nextLine().trim();

            Urun product = products.stream()
                    .filter(p -> p.getÜrünKodu().equals(productId))
                    .findFirst()
                    .orElse(null);

            if (product != null) {
                if (product.getStokDurumu() > 0) {
                    int productQuantity;

                    do {
                        System.out.print("Eklemek istediğiniz miktarı giriniz (pozitif bir sayı): ");
                        while (!sc.hasNextInt()) {
                            System.out.println("Hatalı giriş! Lütfen sayısal bir değer giriniz.");
                            sc.next();
                        }
                        productQuantity = sc.nextInt();
                        sc.nextLine();

                        if (productQuantity <= 0) {
                            System.out.println("Miktar pozitif bir sayı olmalıdır.");
                        } else if (productQuantity > product.getStokDurumu()) {
                            System.out.println("Stok yetersiz! Maksimum miktar: " + product.getStokDurumu());
                        }
                    } while (productQuantity <= 0 || productQuantity > product.getStokDurumu());

                    Sepet existingItem = cart.stream()
                            .filter(item -> item.getUrun().equals(product))
                            .findFirst()
                            .orElse(null);

                    if (existingItem != null) {
                        existingItem.setMiktar(existingItem.getMiktar() + productQuantity);
                        System.out.println("Sepetteki ürün miktarı güncellendi: " + product.getÜrünAdı() +
                                " (Toplam Miktar: " + existingItem.getMiktar() + ")");
                    } else {
                        cart.add(new Urun(product, productQuantity));
                        System.out.println("Ürün sepete eklendi: " + product.getÜrünAdı() +
                                " (Miktar: " + productQuantity + ")");
                    }

                    product.setStokDurumu(product.getStokDurumu() - productQuantity);
                    System.out.println("Ürün stok durumu güncellendi: " + product.getStokDurumu());
                } else {
                    System.out.println("Seçilen ürün stokta yok.");
                }
            } else {
                System.out.println("Girdiğiniz ürün ID'si listede bulunamadı. Lütfen kontrol ediniz.");
            }

            System.out.println("Cart list size: " + products.size());
            System.out.println("Cart1 list size: " + cart.size());

            saveToFile(products, cart);

            System.out.println("İşleme devam etmek için 1, çıkmak için 0 giriniz:");
            try {
                select = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Hatalı giriş! Lütfen 0 veya 1 giriniz.");
                sc.nextLine();
                select = 1;
            }
        } while (select != 0);

        listCart(products);
        return products;
    }

    public static void sepeteEkle(Urun urun, List<Urun> cart) {
        if (urun != null) {
            cart.add(urun);
            System.out.println("Ürün sepete eklendi: " + urun.getÜrünAdı());
        } else {
            System.out.println("Null bir ürün sepete eklenemez.");
        }
    }

    public static void saveToFile(List<Urun> cart, List<Urun> cart1) {
        try (FileWriter writer = new FileWriter("sepet.txt")) {
            System.out.println("Dosya yazmaya başlıyor...");

            for (Urun item : cart) {
                if (item != null) {
                    writer.write(item.getÜrünKodu() + " - " +
                            item.getÜrünAdı() + " - " +
                            item.getStokDurumu() + "\n");
                    System.out.println("Yazılan ürün: " + item.getÜrünAdı());
                }
            }

            for (Urun item : cart1) {
                if (item != null) {
                    writer.write(item.getÜrünKodu() + " - " +
                            item.getÜrünAdı() + " - " +
                            item.getStokDurumu() + "\n");
                    System.out.println("Sepetten yazılan ürün: " + item.getÜrünAdı());
                }
            }

            System.out.println("Sepet dosyaya başarıyla kaydedildi.");
        } catch (IOException e) {
            System.out.println("Sepeti dosyaya kaydederken bir hata oluştu: " + e.getMessage());
        }

    }
    // Sepeti Dosyaya Kaydetme
    public static void saveToFile1(List<Urun> cart, List<Urun> cart1) {
        try (FileWriter writer = new FileWriter("sepet.txt")) {
            System.out.println("Dosya yazmaya başlıyor...");

            // Ürünleri yazdır (cart)
            for (Urun item : cart) {
                if (item != null) { // Null kontrolü
                    writer.write(item.getÜrünKodu() + " - " +
                            item.getÜrünAdı() + " - " +
                            item.getStokDurumu() + "\n");
                    System.out.println("Yazılan ürün: " + item.getÜrünAdı());
                } else {
                    System.out.println("Cart içinde null ürün atlandı.");
                }
            }

            // Sepetteki ürünleri yazdır (cart1)
            for (Urun item : cart1) {
                if (item != null) { // Null kontrolü
                    writer.write(item.getÜrünKodu() + " - " +
                            item.getÜrünAdı() + " - " +
                            item.getMiktar() + "\n");
                    System.out.println("Sepetten yazılan ürün: " + item.getÜrünAdı());
                } else {
                    System.out.println("Cart1 içinde null ürün atlandı.");
                }
            }

            System.out.println("Sepet dosyaya başarıyla kaydedildi.");
        } catch (IOException e) {
            System.out.println("Sepeti dosyaya kaydederken bir hata oluştu: " + e.getMessage());
        }
    }

    // Sepeti Dosyaya Kaydetme
    public static void saveToFile11(List<Urun> cart, List<Urun> cart1) {
        try (FileWriter writer = new FileWriter("sepet.txt")) {
            System.out.println("Dosya yazmaya başlıyor...");

            // Ürünleri yazdır
            for (Urun item : cart) {
                writer.write(item.getÜrünKodu() + " - " +
                        item.getÜrünAdı() + " - " +
                        item.getStokDurumu() + "\n");
                System.out.println("Yazılan ürün: " + item.getÜrünAdı());
            }

            // Sepetteki ürünleri yazdır
            for (Urun item : cart1) {
                writer.write(item.getÜrünKodu() + " - " +
                        item.getÜrünAdı() + " - " +
                        item.getMiktar() + "\n");
                System.out.println("Sepetten yazılan ürün: " + item.getÜrünAdı());
            }

            System.out.println("Sepet dosyaya başarıyla kaydedildi.");
        } catch (IOException e) {
            System.out.println("Sepeti dosyaya kaydederken bir hata oluştu: " + e.getMessage());
        }
    }


    // Sepete Eklenmemiş Ürünleri Listeleme
    public static void listUnaddedProducts(List<Urun> products, List<Urun> cart) {
        System.out.println("Sepete eklenmemiş ürünler:");
        System.out.printf("%-20s %-20s %-15s %-10s%n", "ÜRÜN ID", "ÜRÜN ADI", "STOK", "FİYAT");
        System.out.printf("%-20s %-20s %-15s %-10s%n", "-------", "--------", "----", "-----");

        boolean found = false;
        for (Urun product : products) {
            boolean isInCart = cart.stream().anyMatch(item -> item.getUrun().equals(product));
            if (product.getStokDurumu() > 0 && !isInCart) {
                System.out.printf("%-20s %-20s %-15d %-10.2s%n", product.getÜrünKodu(), product.getÜrünAdı(),
                        product.getStokDurumu(), product.getFiyat());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Tüm ürünler sepette veya stokta yok.");
        }
    }

    // Sepeti Listeleme
    public static boolean listCart(List<Urun> cart) {
        System.out.println("Sepetinizdeki ürünler:");
        System.out.printf("%-20s %-20s %-15s %-10s %-10s%n", "ÜRÜN ID", "ÜRÜN ADI", "MİKTAR", "FİYAT", "TOPLAM");
        System.out.printf("%-20s %-20s %-15s %-10s %-10s%n", "-------", "--------", "------", "-----", "------");

        if (cart.isEmpty()) {
            System.out.println("Sepetiniz boş.");
            return false;
        }

        for (Urun item : cart) {
            double total = item.getMiktar() * item.getFiyat();
            System.out.printf("%-20s %-20s %-15d %-10.2f %-10.2f%n",
                    item.getÜrünKodu(),
                    item.getÜrünAdı(),
                    Sepet.getMiktar(),
                    item.getFiyat(),
                    total);
        }
        return false;
    }

    // System.out.println("6-Kargo secimi ve ödeme secenekleri");

    /*//satış
    private LocalDateTime tarih;
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
    private String kargoDurumu;*/

    public SepetService() {
    }

    private Sepet sepet; // Sepet nesnesini yönetecek

        // Constructor
        public SepetService(Sepet sepet) {
            this.sepet = sepet;
        }

        // Kredi Kartı ile Ödeme
        public void krediKartiIleOdeme(String krediKartBilgisi, String faturaBilgileri) {
            sepet.setKrediCart(krediKartBilgisi);
            sepet.setFaturaBilgileri(faturaBilgileri);
            sepet.setTamamlandı("Evet");
            System.out.println("Kredi Kartı ile ödeme tamamlandı.");
        }

        // Kapıda Ödeme
        public void kapidaOdeme(String faturaBilgileri) {
            sepet.setKapıdaOdeme("Kapıda Ödeme Seçildi");
            sepet.setFaturaBilgileri(faturaBilgileri);
            sepet.setTamamlandı("Evet");
            System.out.println("Kapıda ödeme seçildi ve işlem tamamlandı.");
        }

        // Satış Bilgilerini Görüntüle
        public void satisBilgileriniGoster() {
            System.out.println("Satış Tarihi: " + sepet.getTarih());
            System.out.println("Tamamlandı: " + sepet.getTamamlandı());
            System.out.println("Kredi Kartı: " + sepet.getKrediCart());
            System.out.println("Kapıda Ödeme: " + sepet.getKapıdaOdeme());
            System.out.println("Fatura Bilgileri: " + sepet.getFaturaBilgileri());
        }
    ///System.out.println("5- Satın alınan ürünleri görüntüleme ve kargoya verme");







    /*///  admin kısmı
    public void kargoIslemleri(String secim, String deger) {
        switch (secim.toLowerCase()) {
            case "kargo takip no":
                String takipNo = UUID.randomUUID().toString();
                sepet.setKargoTakipNo(takipNo);
                System.out.println("Kargo Takip Numarası oluşturuldu: " + takipNo);
                break;

            case "teslimat süresi":
                sepet.setTeslimatSüresi(deger);
                System.out.println("Teslimat süresi belirlendi: " + deger);
                break;

            case "kargo ücreti":
                sepet.setKargoUcreti(deger);
                System.out.println("Kargo ücreti belirlendi: " + deger);
                break;

            case "yurtiçi":
                sepet.setYurtici("Yurtiçi Kargo");
                sepet.setYurtdısı(null);
                System.out.println("Kargo türü yurtiçi olarak belirlendi.");
                break;

            case "yurtdışı":
                sepet.setYurtdısı("Yurtdışı Kargo");
                sepet.setYurtici(null);
                System.out.println("Kargo türü yurtdışı olarak belirlendi.");
                break;

            case "kargo durumu":
                sepet.setKargoDurumu(deger);
                System.out.println("Kargo durumu güncellendi: " + deger);
                break;

            default:
                System.out.println("Geçersiz bir işlem seçildi: " + secim);
        }
    }

     */

        // Kargo İşlemleri

        // Kargo Takip Numarası Oluştur
        public void kargoTakipNoOlustur() {
            String takipNo = UUID.randomUUID().toString();
            sepet.setKargoTakipNo(takipNo);
            System.out.println("Kargo Takip Numarası: " + takipNo);
        }

        // Teslimat Süresi Belirle
        public void teslimatSuresiBelirle(String teslimatSuresi) {
            sepet.setTeslimatSüresi(teslimatSuresi);
            System.out.println("Teslimat süresi belirlendi: " + teslimatSuresi);
        }

        // Kargo Ücreti Belirle
        public void kargoUcretiBelirle(String ucret) {
            sepet.setKargoUcreti(ucret);
            System.out.println("Kargo ücreti belirlendi: " + ucret);
        }

        // Yurtiçi veya Yurtdışı Kargo Belirle
        public void kargoKonumuBelirle(boolean yurtiçi) {
            if (yurtiçi) {
                sepet.setYurtici("Yurtiçi Kargo");
                sepet.setYurtdısı(null);
            } else {
                sepet.setYurtdısı("Yurtdışı Kargo");
                sepet.setYurtici(null);
            }
            System.out.println("Kargo konumu belirlendi: " + (yurtiçi ? "Yurtiçi" : "Yurtdışı"));
        }

        // Kargo Durumu Güncelle
        public void kargoDurumuGuncelle(String durum) {
            sepet.setKargoDurumu(durum);
            System.out.println("Kargo durumu güncellendi: " + durum);
        }

        // Satış ve Kargo Bilgilerini Görüntüle
        public void satisVeKargoBilgileriniGoster() {
            System.out.println("Satış Bilgileri:");
            System.out.println("Tamamlandı: " + sepet.getTamamlandı());
            System.out.println("Kredi Kartı: " + sepet.getKrediCart());
            System.out.println("Kapıda Ödeme: " + sepet.getKapıdaOdeme());
            System.out.println("Fatura Bilgileri: " + sepet.getFaturaBilgileri());

            System.out.println("\nKargo Bilgileri:");
            System.out.println("Kargo Takip No: " + sepet.getKargoTakipNo());
            System.out.println("Teslimat Süresi: " + sepet.getTeslimatSüresi());
            System.out.println("Kargo Ücreti: " + sepet.getKargoUcreti());
            System.out.println("Yurtiçi: " + sepet.getYurtici());
            System.out.println("Yurtdışı: " + sepet.getYurtdısı());
            System.out.println("Kargo Durumu: " + sepet.getKargoDurumu());
        }
    }

















