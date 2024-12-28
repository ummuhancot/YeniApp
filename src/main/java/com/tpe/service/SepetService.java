package com.tpe.service;

import com.tpe.domain.Sepet;
import com.tpe.domain.Urun;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    public static void sortAndRemoveAorZ1(List<Urun> nums) {
        // "A" ile başlayan veya "Z" ile biten ürünleri kaldır
        //nums.removeIf(product -> product.getÜrünAdı().startsWith("A") || product.getÜrünAdı().endsWith("Z"));

        // A'dan Z'ye sıralama (ürün adlarına göre)
        List<Urun> sıralıListe = nums.stream()
                .sorted(Comparator.comparing(Urun::getÜrünAdı))
                .collect(Collectors.toList());

        System.out.println("A'dan Z'ye sıralanmış ürünler: " + sıralıListe);

        // Alfabetik olarak ilk sıradaki ürün(ler)in adını bulma
        String minÜrünAdı = nums.stream()
                .map(Urun::getÜrünAdı)
                .min(String::compareTo)
                .orElseThrow(() -> new NoSuchElementException("Liste boş!"));

        // En küçük ada sahip ürün(ler)i yazdırma
        nums.stream()
                .filter(product -> product.getÜrünAdı().equals(minÜrünAdı)) // Ürün adını eşleşmeye göre filtrele
                .forEach(product -> {
                    System.err.println("\nÜrün Kodu: " + product.getÜrünKodu() +
                            "\nÜrün Adı: " + product.getÜrünAdı() +
                            "\nFiyat: " + product.getFiyat() +
                            "\nKategori: " + product.getKategori()+
                            "\nBeden: " +product.getBeden()+
                            "\nRenk:"+product.getRenk()+
                            "\nMalzeme"+product.getMalzeme()+
                            "\nKol Tipi"+product.getKolTipi()+
                            "\nBoy Uzunlugu"+product.getBoyUzunlugu()+
                            "\nÜretici"+product.getUretici()+
                            "\nStok Durumu"+product.getStokDurumu()+
                            "\n");
                });
    }

    // Sepeti Dosyaya Kaydetme
    public static void saveToFile(List<Urun> cart, List<Urun> cart1) {
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
    public static void listCart(List<Urun> cart) {
        System.out.println("Sepetinizdeki ürünler:");
        System.out.printf("%-20s %-20s %-15s %-10s %-10s%n", "ÜRÜN ID", "ÜRÜN ADI", "MİKTAR", "FİYAT", "TOPLAM");
        System.out.printf("%-20s %-20s %-15s %-10s %-10s%n", "-------", "--------", "------", "-----", "------");

        if (cart.isEmpty()) {
            System.out.println("Sepetiniz boş.");
            return;
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
    }
}






