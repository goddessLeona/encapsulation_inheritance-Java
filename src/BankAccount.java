public class BankAccount {

    private String ownerName;
    private String acountsNr;
    private double balance;

    public BankAccount(String ownerName, String acountsNr, double balance) {
        this.ownerName = ownerName;
        this.acountsNr = acountsNr;
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getAcountsNr() {
        return acountsNr;
    }

    public double getBalance() {
        return balance;
    }

    public void setOwnerName(String ownerName) {

        if(ownerName == null){
            System.out.println("Owner Name can not be null.");
            return;
        }

        if(ownerName.matches(".*\\d.*")){
            System.out.println("Owner name can not contain numbers.");
            return;
        }

        if(!ownerName.contains(" ")){
            System.out.println("Owner name must contain both a first name and a last name");
            return;
        }
        this.ownerName = ownerName;
        System.out.println("Owner name successfully updated to: " + ownerName);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "ownerName='" + ownerName + '\'' +
                ", acountsNr='" + acountsNr + '\'' +
                ", balance=" + balance +
                '}';
    }

    public double deposit( double amount){

        if(amount>0){
            balance +=amount;
            System.out.println("\n" + this.ownerName + " you have now made a deposit of: " + amount + " new balance is: " + balance);
        }else if(amount<0){
            System.out.println("You have to deposit a amount higher than 0");
        }
        return balance;
    }

    public double withdraw( double amount) {

        if (amount >0 && balance>= amount) {
            balance -= amount;
            System.out.println("\n" + this.ownerName + " you have now withdrawn: " + amount + " new balance is: " + balance);
        } else if(amount<=0) {
            System.out.println("You have to withraw a positive nr over 0");
        }else{
            System.out.println("You do not have enough balance to do this withdraw");
        }
        return balance;
    }

    public boolean transfer(double amount, BankAccount tillVem  ){

       if(amount>0 && this.balance >= amount){
           this.balance -= amount;
           tillVem.balance += amount;
           System.out.println("\n"+this.ownerName +" transferred " + amount + " to " + tillVem.getOwnerName());
           System.out.println(this.ownerName + "'s new balance: " + this.balance);
           System.out.println(tillVem.getOwnerName() + "'s new balance: " + tillVem.getBalance());

           return true;
       }else if(amount<=0){
           System.out.println("transfer amount have to be positive and more than 0.");
       }else{
           System.out.println("You do not have enough money on your account to transfer that amount.");
       }
       return false;
    }
}
