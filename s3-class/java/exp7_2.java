import java.util.Random;
class RandomNumberGenerator extends Thread{
    public void run(){
        Random r = new Random();
        while(true){
            int num = r.nextInt(100)+2;
            System.out.println("Random Number: "+num);
            if(num%2==0){
                new Even(num).start();
            }else{
                new Odd(num).start();
            }
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
}

class Even extends Thread{
    int num;
    Even(int num){
        this.num = num;
    }
    public void run(){
        System.out.println("Even Number between 1 to "+num+" are:");
        for(int i=2;i<=num;i+=2){
            System.out.print(i+"  ");
        }
        System.out.println();
    }
}

class Odd extends Thread{
    int num;
    Odd(int num){
        this.num = num;
    }
    public void run(){
        System.out.println("Odd Number between 1 to "+num+" are:");
        for(int i=1;i<=num;i+=2){
            System.out.print(i+"  ");
        }
        System.out.println();
    }
}

public class exp7_2{
    public static void main(String[] args){
        RandomNumberGenerator r = new RandomNumberGenerator();
        r.start();
    }
}
