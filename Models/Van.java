package Models;

public class Van extends Vehicle {
    public Van(String licensePlate) {
        super(licensePlate, 500.0);
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