package Tests;
import Models.*;
import Patterns.*;
import Services.*;

public class UnitTests {
    public static void runAllTests() {
        System.out.println("\n--- BİRİM TESTLERİ ÇALIŞTIRILIYOR ---");
        int passed = 0;

        // Test 1: Van Kapasite Limiti Aşımı
        Van van = new Van("34ABC123");
        Models.Package p1 = new Models.Package("P1", 600, PriorityLevel.MEDIUM, "B");
        if (!van.loadPackage(p1)) passed++;

        // Test 2: Van Başarılı Yükleme
        Models.Package p2 = new Models.Package("P2", 200, PriorityLevel.HIGH, "B");
        if (van.loadPackage(p2)) passed++;

        // Test 3: Truck Kapasite Yükleme
        Truck truck = new Truck("34XYZ999");
        Models.Package p3 = new Models.Package("P3", 1500, PriorityLevel.LOW, "C");
        if (truck.loadPackage(p3)) passed++;

        // Test 4: Factory Pattern Doğrulaması
        Vehicle createdVehicle = VehicleFactory.createVehicle("van", "06ANK06");
        if (createdVehicle instanceof Van) passed++;

        // Test 5: Dijkstra Rota Doğruluğu
        RouteOptimizer optimizer = new RouteOptimizer();
        optimizer.addRoute("A", "B", 10);
        optimizer.addRoute("B", "C", 10);
        RouteOptimizer.RouteResult result = optimizer.calculateShortestPath("A", "C");
        if (result.getTotalDistance() == 20) passed++;

        // Test 6: Singleton Tekillik Testi
        DeliveryManager m1 = DeliveryManager.getInstance();
        DeliveryManager m2 = DeliveryManager.getInstance();
        if (m1 == m2) passed++;

        // Test 7: Paket Teslimat Durum Değişimi
        p2.setDelivered(true);
        if (p2.isDelivered()) passed++;

        // Test 8: Araç Yük Temizleme
        van.clearCargo();
        if (van.getCurrentLoadKg() == 0) passed++;

        System.out.println("[TEST SONUCU] " + passed + "/8 Test Başarıyla Geçti!");
        System.out.println("-------------------------------------\n");
    }
}