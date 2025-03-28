public class VehicleSystem {

    //add new cars
    Car carnr1 = new Car("Saab","hejåå",1985,5,"red");
    Car carNr2 = new Car("VW","old",1945,2,"green");

    Motorcycle motoNr1 = new Motorcycle("Harly","soofast",1979,"Heavy");

    Truck truckNr1 = new Truck("BigTruck","bigModel",1999,"Disel");

    public void runSystem(){
        System.out.println("\nStarting Vehicle exercise now");

        carnr1.displayCarInfo();
        carNr2.displayCarInfo();

        motoNr1.allMotorcycleinfo();

        truckNr1.allTruckinfo();

        System.out.println("\nTesting starting the different vehicles");
        carNr2.startEngine();
        motoNr1.startEngine();
        truckNr1.startEngine();

    }

}
