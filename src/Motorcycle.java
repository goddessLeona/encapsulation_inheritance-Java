public class Motorcycle extends Vehicle {


    private String type;

    public Motorcycle(String brand, String model, int year, String type) {
        super(brand,model,year);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Motorcycle{" +
                "type='" + type + '\'' +
                '}';
    }

    public void allMotorcycleinfo(){
        System.out.println("\nInformation about the Motorcycle:");
        displayInfo();
        System.out.println("Type: " + this.type);
    }

    public void startEngine(){
        System.out.println("The motorcycle starting the engine. wroooooom wrooooom");
    }

    public void stopEngine(){
        System.out.println("The motorcycle turning off the engine.");
    }

}
