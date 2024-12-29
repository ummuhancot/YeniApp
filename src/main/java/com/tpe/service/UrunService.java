package com.tpe.service;

import com.tpe.domain.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class UrunService  {
    // Ürünlerin saklanacağı Map, LinkedHashMap kullanılarak eklenme sırası korunur
    public static Map<String, Urun> products = new LinkedHashMap<>();
    public static Scanner sc = new Scanner(System.in);
    // UrunSaveService saveService = new UrunSaveService(); // Ürünlerin dosyaya kaydedilmesi için servis sınıfı
    // Yapıcı metot, uygulama başlarken dosyadan ürünleri yükler



    //System.out.println("1- Bir ürün tanımlayın");
    public static void addProduct(Map<String, Urun> products) {


        int select = 1;
        do {
            // Yeni bir ürün nesnesi oluştur


            // Ürün bilgilerini kullanıcıdan al
            System.out.print("Ürün Adı: ");
            String ÜrünAdı = sc.nextLine().toUpperCase().trim();
            System.out.print("Ürün kategorisini giriniz :");
            String kategori = sc.nextLine().toUpperCase().trim();
            System.out.print("Ürün Fiyatı : ");
            Integer fiyat = sc.nextInt();
            sc.nextLine();


            //Ürün Özelliklerini ekleme objesini oluştur

            System.out.print("Ürün Bedeni : ");
            String Beden = sc.nextLine().toUpperCase().trim();
            System.out.print("Ürün Renk : ");
            String renk = sc.nextLine().toUpperCase().trim();
            System.out.print("Ürün Malzeme : ");
            String malzeme = sc.nextLine().toUpperCase().trim();
            System.out.print("Ürün Kol Tipi : ");
            String ÜrünKolTipi = sc.nextLine().toUpperCase().trim();
            System.out.print("Ürün Boy Uzunluğu : ");
            Integer ÜrünBoyUzunluğu = sc.nextInt();
            sc.nextLine();

            //Ürün Teknik Alanı
            System.out.print("Ürün Stok Durum : ");
            int ÜrünStokDurumu = sc.nextInt();
            sc.nextLine();
            System.out.print("Ürün Üretici : ");
            String ÜrünÜretici = sc.nextLine().toUpperCase().trim();

            // Aynı ürünün mevcut olup olmadığını kontrol et
            for (Urun w : products.values()) {
                if (w.getÜrünAdı().equals(ÜrünAdı)  && w.getKategori().equals(kategori)&&
                        w.getFiyat().equals(fiyat)) {
                    System.out.println("Bu ürün zaten mevcut. Bunun yerine miktarını güncelleyebilirsiniz.");
                    return;
                }
            }

            // Ürün miktarını doğrula ve al
            int productQuantity;
            do {
                System.out.print("Bir miktar giriniz:");
                while (!sc.hasNextInt()) {
                    System.out.println("Geçersiz giriş! Lütfen miktar için sayısal bir değer girin.");
                    sc.next(); // Hatalı girişi temizle
                }
                productQuantity = sc.nextInt();
                sc.nextLine(); // Newline karakterini temizle
                if (productQuantity <= 0) {
                    System.out.println("Miktar pozitif bir sayı olmalıdır.");
                }
            } while (productQuantity <= 0);


            Urun urun = new Urun(ÜrünAdı,kategori,fiyat,Beden,renk,malzeme,ÜrünKolTipi,ÜrünBoyUzunluğu,ÜrünStokDurumu,ÜrünÜretici);



            // Ürün özelliklerini ayarla ve ID'yi oluştur
            urun.setÜrünAdı(ÜrünAdı);
            urun.setKategori(kategori);
            urun.setFiyat(fiyat);

            productId(urun);

            // Ürün ID'sini ayarla

            // Ürünü Map'e ekle
            products.put(urun.getÜrünKodu(), urun);

            System.out.println(urun);

            System.out.println(urun.getÜrünKodu());

            // İşleme devam veya çıkış seçeneği sunma
            System.out.println("Press 1 to CONTINUE the process or 0 to EXIT.");
            try {
                select = sc.nextInt();
                sc.nextLine();  // Satır sonu karakterini temizlemek için
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter 0 or 1.");
                sc.nextLine();  // Hatalı girişi temizlemek için
                select = 1;     // Hatalı giriş durumunda döngüyü devam ettirmek için
            }

        }while (select !=0);

        listProduct(products);
    }


    // Ürün ID'sini oluşturur
    public static void productId(Urun urun) {

        try {

            // ID'yi ürün adı ve yıl bilgisiyle oluştur, counter'ı kullanarak benzersiz yap
            urun.setÜrünKodu(urun.getÜrünAdı().toUpperCase().substring(0,3) + LocalDate.now().getYear()+Urun.counter);
            Urun.counter++;
        } catch (StringIndexOutOfBoundsException e) {
            // Eğer ürün adı kısa ise "NULL" kullanarak ID oluştur
            urun.setÜrünKodu("\uD83D\uDE08"+LocalDate.now().getYear() + Urun.counter);
            Urun.counter++;
        }
    }


/*
    public void listProduct (List<Urun > urun){

        for (Urun product : urun) {
            // Eğer ürün miktarı 0 ise kırmızı renkte yazdır
            if (product.getUrunTeknikAlanı().getStokDurumu() == 0) {
                System.err.printf("stok bulunmamakta");
            } else {
                System.out.printf(product.getÜrünKodu(), product.getÜrünAdı(), product.getUrunTeknikAlanı().getUretici(), product.getUrunTeknikAlanı().getStokDurumu(), product.getFiyat(), product.getKategori());
            }
        }

    }
    */

    // ürünleri listeler
    public static void listProduct(Map<String, Urun> products) {
        List<Urun> urunList =new ArrayList<>(products.values());
        for (Urun product : urunList) {
            // Eğer ürün miktarı 0 ise kırmızı renkte yazdır
            if (product.getStokDurumu() == 0) {
                System.err.println("\n"+product.getÜrünKodu()+"\n"+product.getÜrünAdı()+"\n"+product.getFiyat()+"\n"+product.getKategori()+"\n");

            } else {
                System.out.println("\n"+product.getÜrünKodu()+"\n"+product.getÜrünAdı()+"\n"+product.getFiyat()+"\n"+product.getKategori()+"\n");

                System.err.println("calıştı");
            }
        }
    }

    //System.out.println("5- Ürünleri filtreleme (max,min,A dan - Z ye )");
    public static void AdminlistProductWithSorting(Map<String, Urun> product) {
        // Kullanıcıdan sıralama tercihini al
        Scanner sc = new Scanner(System.in);
        int select = -1;

        while (select == -1) { // Geçerli bir seçim yapılana kadar döngü
            try {
                System.out.println("Choose sorting criteria: ");
                System.out.println("1. Sort by Quantity");
                System.out.println("2. Sort by Shelf Number");
                System.out.println("3. Sort by Product Name");
                System.out.println("4. Sort by Productor Name");  // Üretici ismine göre sıralama seçeneği
                System.out.print("Enter your choice (1-4): ");
                select = sc.nextInt();

                // Geçersiz seçim kontrolü
                if (select < 1 || select > 3) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                    select = -1; // Döngüyü tekrar çalıştırmak için choice değeri sıfırlanır
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number between 1 and 3.");
                sc.nextLine(); // Geçersiz girdiyi temizlemek için
                select = -1; // Döngüyü tekrar çalıştırmak için choice değeri sıfırlanır
            }


            List<Urun> productList = new ArrayList<>(products.values());

            // Seçilen tercihe göre sıralama
            switch (select) {
                case 1: // max göre sıralama
                    maximum(productList);
                    break;
                case 2: // min numarasına göre sıralama
                    minimum(productList);
                    break;
                case 3: // Ürün ismine göre sıralama
                    sortAndRemoveAorZ1(productList);
                    break;
            }
        }

    }
    public static void minimum(List<Urun> nums) {
        // Küçükten büyüğe sıralama (fiyatlara göre)
        List<Urun> sıralıListe = nums.stream()
                .sorted(Comparator.comparingInt(Urun::getFiyat))
                .collect(Collectors.toList());

        System.out.println("Küçükten büyüğe sıralanmış ürünler: " + sıralıListe);

        // En düşük fiyatı bulma
        int minFiyat = nums.stream()
                .mapToInt(Urun::getFiyat)
                .min()
                .orElseThrow(() -> new NoSuchElementException("Liste boş!"));

        // En düşük fiyatlı ürünleri yazdırma
        nums.stream().filter(product -> product.getFiyat() == minFiyat) // Fiyatı minFiyat olan ürünleri filtrele
                .forEach(product -> {
                    // Ürün bilgilerini yazdırma
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
    public static void maximum(List<Urun> nums) {
        // Büyükten küçüğe sıralama (fiyatlara göre)
        List<Urun> sıralıListe = nums.stream()
                .sorted(Comparator.comparingInt(Urun::getFiyat).reversed())
                .collect(Collectors.toList());

        System.out.println("Büyükten küçüğe sıralanmış ürünler: " + sıralıListe);

        // En yüksek fiyatı bulma
        int maxFiyat = nums.stream()
                .mapToInt(Urun::getFiyat)
                .max()
                .orElseThrow(() -> new NoSuchElementException("Liste boş!"));

        // En yüksek fiyatlı ürünleri yazdırma
        nums.stream()
                .filter(product -> product.getFiyat() == maxFiyat) // Fiyatı maxFiyat olan ürünleri filtrele
                .forEach(product -> {
                    // Ürün bilgilerini yazdırma
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

    // System.out.println("3- tüm ürünleri listeleyiniz");
    public static void enterProduct(Map<String, Urun> products) {
// Ürünleri listele
        List<Urun> productList = new ArrayList<>(products.values());

        for (Urun product : productList) {
            // Eğer ürün miktarı 0 ise kırmızı renkte yazdır
            if (product.getStokDurumu() == 0) {
                System.err.println(product.getÜrünKodu()+"\n"+ product.getÜrünAdı()+"\n"+ product.getUretici()+"\n"+ product.getStokDurumu()+"\n"+
                        product.getFiyat()+"\n"+ product.getKategori()+"\n"+product.getBeden()+"\n"+product.getRenk()+"\n"+product.getKolTipi()+"\n"+ product.getBoyUzunlugu());
            } else {
                System.out.println(product.getÜrünKodu()+"\n"+ product.getÜrünAdı()+"\n"+ product.getUretici()+"\n"+ product.getStokDurumu()+"\n"+
                        product.getFiyat()+"\n"+ product.getKategori()+"\n"+product.getBeden()+"\n"+product.getRenk()+"\n"+product.getKolTipi()+"\n"+ product.getBoyUzunlugu());
            }
        }
    }
    //System.out.println("2- id si verilen Ürünleri listele");
    public static void listIdProduct(Map<String, Urun> products) {
        // Kullanıcıdan ürün adını al
        Scanner scanner = new Scanner(System.in);
        System.out.print("Görüntülemek istediğiniz ürün adını girin: ");
        String searchedName = scanner.nextLine().trim();

        // Ürünleri ara ve eşleşenleri yazdır
        boolean found = false;
        for (Urun product : products.values()) {
            if (product.getÜrünAdı().equalsIgnoreCase(searchedName)) {
                found = true;
                if (product.getStokDurumu() == 0) {
                    // Stok durumu 0 ise kırmızı renkte yazdır
                    System.err.println("\nÜrün Bulundu:\n" +
                            "Ürün Kodu: " + product.getÜrünKodu() + "\n" +
                            "Ürün Adı: " + product.getÜrünAdı() + "\n" +
                            "Fiyat: " + product.getFiyat() + "\n" +
                            "Kategori: " + product.getKategori() + "\n");
                } else {
                    // Stok durumu 0 değilse normal yazdır
                    System.out.println("\nÜrün Bulundu:\n" +
                            "Ürün Kodu: " + product.getÜrünKodu() + "\n" +
                            "Ürün Adı: " + product.getÜrünAdı() + "\n" +
                            "Fiyat: " + product.getFiyat() + "\n" +
                            "Kategori: " + product.getKategori() + "\n");
                }
            }
        }

        // Ürün bulunamazsa bilgi mesajı ver
        if (!found) {
            System.err.println("Aradığınız isimde bir ürün bulunamadı.");
        }
    }

    //System.out.println("4- id si verilen ürünü silme ");
    public static void deleteProductById(Map<String, Urun> products) {
        // Kullanıcıdan ürün ID'sini al
        Scanner scanner = new Scanner(System.in);
        System.out.print("Silmek istediğiniz ürün ID'sini girin: ");
        String productId = scanner.nextLine().trim();

        // Map'ten ürün ID'sine göre sil
        if (products.containsKey(productId)) {
            Urun removedProduct = products.remove(productId);
            System.out.println("Ürün başarıyla silindi:\n" +
                    "Ürün Kodu: " + removedProduct.getÜrünKodu() + "\n" +
                    "Ürün Adı: " + removedProduct.getÜrünAdı() + "\n" +
                    "Fiyat: " + removedProduct.getFiyat() + "\n" +
                    "Kategori: " + removedProduct.getKategori() + "\n");
        } else {
            System.err.println("Bu ID'ye sahip bir ürün bulunamadı.");
        }
    }

    public void putProductOnShelf(Map<String, Urun> products) {

    }


    public void productOutput(Map<String, Urun> products) {

    }


    public void removeProduct(Map<String, Urun> products) {

    }


    public void clearProducts(Map<String, Urun> products) {

    }

    //System.out.println("3-Ürün ismine göre arama yap");
    public static void searchProductByName(Map<String, Urun> products) {
        // Kullanıcıdan ürün adını al
        Scanner scanner = new Scanner(System.in);
        System.out.print("Aramak istediğiniz ürün adını girin: ");
        String productName = scanner.nextLine().trim();

        // Ürün arama
        boolean found = false;
        for (Urun product : products.values()) {
            if (product.getÜrünAdı().equalsIgnoreCase(productName)) {
                found = true;
                System.out.println("\nÜrün Bulundu:\n" +
                        "Ürün Kodu: " + product.getÜrünKodu() + "\n" +
                        "Ürün Adı: " + product.getÜrünAdı() + "\n" +
                        "Fiyat: " + product.getFiyat() + "\n" +
                        "Kategori: " + product.getKategori() + "\n" +
                        "Stok Durumu: " + product.getStokDurumu() + "\n");
            }
        }

        // Eğer ürün bulunamazsa mesaj göster
        if (!found) {
            System.err.println("Aradığınız isimde bir ürün bulunamadı.");
        }
    }

    //System.out.println("3-Ürün ismine göre arama yap");
    //Tercihe göre sıralı liste ----> müsteri kısmına koy
    public static void MusterilistProductWithSorting(Map<String, Urun> product) {
        // Kullanıcıdan sıralama tercihini al
        Scanner sc = new Scanner(System.in);
        int select = -1;

        while (select == -1) { // Geçerli bir seçim yapılana kadar döngü
            try {
                System.out.println("Choose sorting criteria: ");
                System.out.println("1. Sort by Quantity");
                System.out.println("2. Sort by Shelf Number");
                System.out.println("3. Sort by Product Name");
                System.out.println("4. Sort by Productor Name");  // Üretici ismine göre sıralama seçeneği
                System.out.print("Enter your choice (1-4): ");
                select = sc.nextInt();

                // Geçersiz seçim kontrolü
                if (select < 1 || select > 3) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                    select = -1; // Döngüyü tekrar çalıştırmak için choice değeri sıfırlanır
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number between 1 and 3.");
                sc.nextLine(); // Geçersiz girdiyi temizlemek için
                select = -1; // Döngüyü tekrar çalıştırmak için choice değeri sıfırlanır
            }


            List<Urun> productList = new ArrayList<>(products.values());

            // Seçilen tercihe göre sıralama
            switch (select) {
                case 1: // max göre sıralama
                    maximum1(productList);
                    break;
                case 2: // min numarasına göre sıralama
                    minimum1(productList);
                    break;
                case 3: // Ürün ismine göre sıralama
                    sortAndRemoveAorZ(productList);
                    break;
            }
        }

    }

    public static void minimum1(List<Urun> nums) {
        // Küçükten büyüğe sıralama (fiyatlara göre)
        List<Urun> sıralıListe = nums.stream()
                .sorted(Comparator.comparingInt(Urun::getFiyat))
                .collect(Collectors.toList());

        System.out.println("Küçükten büyüğe sıralanmış ürünler: " + sıralıListe);

        // En düşük fiyatı bulma
        int minFiyat = nums.stream()
                .mapToInt(Urun::getFiyat)
                .min()
                .orElseThrow(() -> new NoSuchElementException("Liste boş!"));

        // En düşük fiyatlı ürünleri yazdırma
        nums.stream().filter(product -> product.getFiyat() == minFiyat) // Fiyatı minFiyat olan ürünleri filtrele
                .forEach(product -> {
                    // Ürün bilgilerini yazdırma
                    System.err.println("\nÜrün Kodu: " + product.getÜrünKodu() +
                            "\nÜrün Adı: " + product.getÜrünAdı() +
                            "\nFiyat: " + product.getFiyat() +
                            "\nKategori: " + product.getKategori() + "\n");
                });
    }

    public static void maximum1(List<Urun> nums) {
        // Büyükten küçüğe sıralama (fiyatlara göre)
        List<Urun> sıralıListe = nums.stream()
                .sorted(Comparator.comparingInt(Urun::getFiyat).reversed())
                .collect(Collectors.toList());

        System.out.println("Büyükten küçüğe sıralanmış ürünler: " + sıralıListe);

        // En yüksek fiyatı bulma
        int maxFiyat = nums.stream()
                .mapToInt(Urun::getFiyat)
                .max()
                .orElseThrow(() -> new NoSuchElementException("Liste boş!"));

        // En yüksek fiyatlı ürünleri yazdırma
        nums.stream()
                .filter(product -> product.getFiyat() == maxFiyat) // Fiyatı maxFiyat olan ürünleri filtrele
                .forEach(product -> {
                    // Ürün bilgilerini yazdırma
                    System.err.println("\nÜrün Kodu: " + product.getÜrünKodu() +
                            "\nÜrün Adı: " + product.getÜrünAdı() +
                            "\nFiyat: " + product.getFiyat() +
                            "\nKategori: " + product.getKategori() + "\n");
                });
    }

    public static void sortAndRemoveAorZ(List<Urun> nums) {
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
                            "\nKategori: " + product.getKategori() + "\n");
                });
    }




}