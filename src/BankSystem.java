public class BankSystem {

    BankAccount acountNr1 = new BankAccount("John Person","2833892",400);
    BankAccount acountNr2 = new BankAccount("Tove Person","2839882",200);


    public void runBank(){
        System.out.println("\nNow runing exersice Bankacount");

        System.out.println("\nThis is the curent bank information:");
        System.out.println(acountNr1);

        acountNr1.deposit(200);
        System.out.println("\nThis is the curent bank information:");
        System.out.println(acountNr1);

        acountNr1.withdraw(300);
        System.out.println("\nThis is the curent bank information:");
        System.out.println(acountNr1);
        System.out.println(acountNr2);

        acountNr1.transfer(100.50,acountNr2);
        System.out.println("\nThis is the curent bank information:");
        System.out.println(acountNr1);
        System.out.println(acountNr2);

        System.out.println("\nExercise banksystem done");
    }
}
