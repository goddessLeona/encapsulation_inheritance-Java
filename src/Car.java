public class Car extends Vehicle {

    private int doors;
    private String color;

    public Car(String brand, String model, int year, int doors, String color) {
        super (brand,model,year);
        this.doors = doors;
        this.color = color;
    }

    public int getDoors() {
        return doors;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "doors=" + doors +
                ", color='" + color + '\'' +
                '}';
    }

    public void displayCarInfo() {
        System.out.println("\nInformation about the car:");
        displayInfo();  // Call to the parent method
        System.out.println("Doors: " + doors + ", Color: " + color);
    }

    public void startEngine(){
        System.out.println("The car starting the engine, wrom wroom.");
    }

    public void stopEngine(){
        System.out.println("The car turning off the engine.");
    }

}
