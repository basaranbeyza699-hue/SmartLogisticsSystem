package Patterns;
import Models.*;

public class VehicleFactory {
    public static Vehicle createVehicle(String type, String licensePlate) {
        if (type.equalsIgnoreCase("van")) {
            return new Van(licensePlate);
        } else if (type.equalsIgnoreCase("truck")) {
            return new Truck(licensePlate);
        }
        throw new IllegalArgumentException("Geçersiz araç türü!");
    }
}