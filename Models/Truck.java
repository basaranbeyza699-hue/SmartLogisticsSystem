package Models;

public class Truck extends Vehicle {
    public Truck(String licensePlate) {
        super(licensePlate, 3000.0);
    }

    @Override
    public boolean loadPackage(Package pkg) {
        if (currentLoadKg + pkg.getWeightKg() <= getCapacityKg()) {
            getCargoList().add(pkg);
            currentLoadKg += pkg.getWeightKg();
            return true;
        }
        return false;
    }
}