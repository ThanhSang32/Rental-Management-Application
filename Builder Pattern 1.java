import java.util.Scanner;

interface Contract {
    void buildContractID(String contractID);
    void buildPropertyID(String propertyID);
    void buildTenantID(String tenantID);
    void buildRentAmount(double rentAmount);
    Contract signContract();
}

class PermanentContract implements Contract {
    private String contractID;
    private String propertyID;
    private String tenantID;
    private double rentAmount;

    @Override
    public void buildContractID(String contractID) {
        this.contractID = contractID;
    }
    
    @Override
    public void buildPropertyID(String propertyID) {
        this.propertyID = propertyID;
    }

    @Override
    public void buildTenantID(String tenantID) {
        this.tenantID = tenantID;
    }
    
    @Override
    public void buildRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }
    
    @Override
    public Contract signContract() {
        return this;
    }
}

class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Contract ID: ");
        String contractID = scanner.nextLine();
        
        System.out.print("Enter Property ID: ");
        String propertyID = scanner.nextLine();
        
        System.out.print("Enter Tenant ID: ");
        String tenantID = scanner.nextLine();
        
        System.out.print("Enter Rent Amount: ");
        double rentAmount = scanner.nextDouble();
        
        Contract contract = new PermanentContract();
        contract.buildContractID(contractID);
        contract.buildPropertyID(propertyID);
        contract.buildTenantID(tenantID);
        contract.buildRentAmount(rentAmount);
        
        Contract signedContract = contract.signContract();
        System.out.println("Contract successfully signed!");
        
        scanner.close();
    }
}