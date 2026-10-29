package Models;

public class Package {
    private String id;
    private double weightKg;
    private PriorityLevel priority;
    private String destinationNode;
    private boolean isDelivered;

    public Package(String id, double weightKg, PriorityLevel priority, String destinationNode) {
        this.id = id;
        this.weightKg = weightKg;
        this.priority = priority;
        this.destinationNode = destinationNode;
        this.isDelivered = false;
    }

    public String getId() { return id; }
    public double getWeightKg() { return weightKg; }
    public PriorityLevel getPriority() { return priority; }
    public String getDestinationNode() { return destinationNode; }
    public boolean isDelivered() { return isDelivered; }
    public void setDelivered(boolean delivered) { isDelivered = delivered; }

    @Override
    public String toString() {
        return "[Paket ID: " + id + "] Ağırlık: " + weightKg + "kg | Öncelik: " + priority +
                " | Hedef: Node " + destinationNode + " | Durum: " + (isDelivered ? "Teslim Edildi" : "Beklemede");
    }
}