import Models.*;
import Patterns.*;
import Services.*;
import Tests.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Başlangıçta 8 birim testi otomatik çalışır
        UnitTests.runAllTests();

        DeliveryManager manager = DeliveryManager.getInstance();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("=== AKILLI LOJİSTİK VE ROTA YÖNETİM SİSTEMİ ===");
            System.out.println("1. Yeni Paket Ekle");
            System.out.println("2. Tüm Paketleri Listele");
            System.out.println("3. Araca Paket Yükle ve Rota Hesapla");
            System.out.println("4. Çıkış");
            System.out.print("Seçiminiz: ");

            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Paket ID: ");
                    String id = sc.nextLine();
                    System.out.print("Ağırlık (kg): ");
                    double weight = Double.parseDouble(sc.nextLine());
                    System.out.print("Hedef Düğüm (A, B, C, D, E): ");
                    String dest = sc.nextLine().toUpperCase();

                    Models.Package pkg = new Models.Package(id, weight, PriorityLevel.HIGH, dest);
                    manager.addPackage(pkg);
                    System.out.println("-> Paket eklendi ve dosyaya kaydedildi.\n");
                    break;

                case "2":
                    System.out.println("\n--- KAYITLI PAKETLER ---");
                    for (Models.Package p : manager.getPackages()) {
                        System.out.println(p);
                    }
                    System.out.println();
                    break;

                case "3":
                    System.out.print("Araç Tipi (Van/Truck): ");
                    String type = sc.nextLine();
                    System.out.print("Plaka: ");
                    String plate = sc.nextLine();

                    Vehicle vehicle = VehicleFactory.createVehicle(type, plate);

                    for (Models.Package p : manager.getPackages()) {
                        if (!p.isDelivered()) {
                            if (vehicle.loadPackage(p)) {
                                p.setDelivered(true);
                                System.out.println("-> " + p.getId() + " numaralı paket " + vehicle.getLicensePlate() + " plakalı araca yüklendi.");

                                RouteOptimizer.RouteResult route = manager.getOptimizer().calculateShortestPath("A", p.getDestinationNode());
                                System.out.println("   [En Uygun Rota]: " + String.join(" -> ", route.getPath()) + " (Mesafe: " + route.getTotalDistance() + " km)");
                            } else {
                                System.out.println("-> " + p.getId() + " numaralı paket araç kapasitesini aştığı için yüklenemedi!");
                            }
                        }
                    }
                    manager.saveState();
                    System.out.println();
                    break;

                case "4":
                    running = false;
                    System.out.println("Programdan çıkılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim!\n");
                    break;
            }
        }
        sc.close();
    }
}