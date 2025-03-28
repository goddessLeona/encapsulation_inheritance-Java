public class Main {
    public static void main(String[] args) {
        System.out.println("Now started up the program");

        BankSystem system = new BankSystem();
        system.runBank();

        VehicleSystem vehicleSystem = new VehicleSystem();
        vehicleSystem.runSystem();
    }
}