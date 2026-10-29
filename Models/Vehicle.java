package Models;
import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle {
    private String licensePlate;
    private double capacityKg;
    protected double currentLoadKg;
    private List<Package> cargoList;

    public Vehicle(String licensePlate, double capacityKg) {
        this.licensePlate = licensePlate;
        this.capacityKg = capacityKg;
        this.currentLoadKg = 0;
        this.cargoList = new ArrayList<>();
    }

    public String getLicensePlate() { return licensePlate; }
    public double getCapacityKg() { return capacityKg; }
    public double getCurrentLoadKg() { return currentLoadKg; }
    public List<Package> getCargoList() { return cargoList; }

    public abstract boolean loadPackage(Package pkg);

    public void clearCargo() {
        cargoList.clear();
        currentLoadKg = 0;
    }
}