interface Account {
    void deposit(double amt);
    void withdraw(double amt);
    double viewBalance();
}

class SavingAccount implements Account{

    private double bal;
    private double intrate;

    public SavingAccount(double bal,double intrate){
        this.bal=bal;
        this.intrate=intrate;
    }

    public void deposit(double amt){
        if(amt>0){
            bal+=amt;
            System.out.println("deposited :"+amt);
            System.out.println("new balance :"+bal);
        }
        else{
            System.out.println("invalid amount");
        }
    }

    public void withdraw(double amt){
        if(amt>0 && bal>=amt){
            bal-=amt;
            System.out.println("withdrawn :"+amt);
            System.out.println("new balance :"+bal);
        }
        else{
            System.out.println("invalid amount");
        }
    }

    public double viewBalance(){
        return bal;
    }

    public void addInterest(){
        bal+=bal*intrate/100;
    }
}

class CurrentAccount implements Account{

    private double bal;
    private double overdraft;

    public CurrentAccount(double bal,double overdraft){
        this.bal=bal;
        this.overdraft=overdraft;
    }

    public void deposit(double amt){
        if(amt>0){
            bal+=amt;
            System.out.println("deposited :"+amt);
            System.out.println("new balance :"+bal);
        }
        else{
            System.out.println("invalid amount");
        }
    }

    public void withdraw(double amt){
        if(amt>0 && bal+overdraft>=amt){
            bal-=amt;
            System.out.println("withdrawn :"+amt);
            System.out.println("new balance :"+bal);
        }
        else{
            System.out.println("invalid amount");
        }
    }

    public double viewBalance(){
        return bal;
    }

    public void overdraft(){
        System.out.println("overdraft :"+overdraft);
    }
}

public class exp5_5{
    public static void main(String[] args){
        SavingAccount sa=new SavingAccount(1000,5);
        CurrentAccount ca=new CurrentAccount(1000,500);

        sa.deposit(1000);
        sa.withdraw(500);
        sa.addInterest();
        System.out.println("saving account balance :"+sa.viewBalance());

        ca.deposit(1000);
        ca.withdraw(2500);
        ca.overdraft();
        System.out.println("current account balance :"+ca.viewBalance());
    }
}