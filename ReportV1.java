import java.util.ArrayList;
import java.util.List;

class Property {
    private String name;
    private double rentAmount;
    private String ownerName; 
    private String location;

    public Property(String name, double rentAmount, String ownerName, String location) {
        if (name == null || name.isEmpty() || rentAmount < 0 || ownerName == null || ownerName.isEmpty() || location == null || location.isEmpty()) {
            throw new IllegalArgumentException("Invalid property details.");
        }
        this.name = name;
        this.rentAmount = rentAmount;
        this.ownerName = ownerName;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public double getRentAmount() {
        return rentAmount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getLocation() {
        return location;
    }

    public String getPropertyType() {
        return rentAmount > 2000 ? "Premium" : "Standard";
    }

    public double calculateYearlyRent() {
        return rentAmount * 12;
    }
}

class FinancialReport {
    private String reportTitle;
    private List<Property> properties;
    private double totalRent;

    public FinancialReport(String reportTitle, List<Property> properties) {
        this.reportTitle = reportTitle;
        this.properties = properties;
    }

    public void generateReport() {
        totalRent = 0; 
        System.out.println("Financial Report: " + reportTitle);
        System.out.println("----------------------------");
        for (Property property : properties) {
            printPropertyDetails(property);
            totalRent += property.getRentAmount();
            System.out.println("--------------------");
        }
        System.out.println("Total Rent Amount: $" + totalRent);
    }

    private void printPropertyDetails(Property property) {
        System.out.println("Property: " + property.getName());
        System.out.println("Rent Amount: $" + property.getRentAmount());
        System.out.println("Owner: " + property.getOwnerName());
        System.out.println("Location: " + property.getLocation());
        System.out.println("Type: " + property.getPropertyType());
        System.out.println("Yearly Rent: $" + property.calculateYearlyRent());
    }
}

public class ReportGenerator {
    public static void main(String[] args) {
        List<Property> properties = new ArrayList<>();
        properties.add(new Property("Apartment A", 1500.0, "John Doe", "City Center"));
        properties.add(new Property("House B", 2000.0, "Jane Smith", "Suburb"));
        properties.add(new Property("Condo C", 1800.0, "Bob Johnson", "Downtown"));

        FinancialReport financialReport = new FinancialReport("Monthly Rent Summary", properties);
        financialReport.generateReport();
    }
}