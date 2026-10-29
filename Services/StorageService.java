package Services;
import Models.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StorageService {
    private static final String FILE_PATH = "packages_data.txt";

    public static void savePackages(List<Models.Package> packages) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Models.Package p : packages) {
                writer.println(p.getId() + ";" + p.getWeightKg() + ";" + p.getPriority() + ";" + p.getDestinationNode() + ";" + p.isDelivered());
            }
        } catch (IOException e) {
            System.out.println("Dosya kaydetme hatası: " + e.getMessage());
        }
    }

    public static List<Models.Package> loadPackages() {
        List<Models.Package> packages = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return packages;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 5) {
                    String id = parts[0];
                    double weight = Double.parseDouble(parts[1]);
                    PriorityLevel priority = PriorityLevel.valueOf(parts[2]);
                    String dest = parts[3];
                    boolean delivered = Boolean.parseBoolean(parts[4]);

                    Models.Package p = new Models.Package(id, weight, priority, dest);
                    p.setDelivered(delivered);
                    packages.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası: " + e.getMessage());
        }
        return packages;
    }
}