package Services;
import Models.*;
import java.util.List;

public class DeliveryManager {
    private static DeliveryManager instance;
    private List<Models.Package> packages;
    private RouteOptimizer optimizer;

    private DeliveryManager() {
        packages = StorageService.loadPackages();
        optimizer = new RouteOptimizer();
        setupDefaultMap();
    }

    public static synchronized DeliveryManager getInstance() {
        if (instance == null) {
            instance = new DeliveryManager();
        }
        return instance;
    }

    private void setupDefaultMap() {
        optimizer.addRoute("A", "B", 10);
        optimizer.addRoute("A", "C", 15);
        optimizer.addRoute("B", "D", 12);
        optimizer.addRoute("C", "D", 10);
        optimizer.addRoute("D", "E", 5);
    }

    public void addPackage(Models.Package pkg) {
        packages.add(pkg);
        StorageService.savePackages(packages);
    }

    public void saveState() {
        StorageService.savePackages(packages);
    }

    public List<Models.Package> getPackages() { return packages; }
    public RouteOptimizer getOptimizer() { return optimizer; }
}