public class Truck extends Vehicle {

    private String petrolType;

    public Truck(String brand, String model, int year, String petrolType) {
        super (brand,model,year);
        this.petrolType = petrolType;
    }

    public String getPetrolType() {
        return petrolType;
    }

    @Override
    public String toString() {
        return "Truck{" +
                "petrolType='" + petrolType + '\'' +
                '}';
    }

    public void allTruckinfo(){
        System.out.println("\nInformation about the Truck:");
        displayInfo();
        System.out.println("Petrole type: " + this.petrolType);
    }

    public void startEngine(){
        System.out.println("The truck starting the engine. Big Wroom");
    }

    public void stopEngine(){
        System.out.println("The truck turning off the engine.");
    }


}
