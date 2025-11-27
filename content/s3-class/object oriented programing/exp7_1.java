import java.util.Random;
class RandomNumberGenerator extends Thread{
    public void run(){
        Random r = new Random();
        while(true){
            int num = r.nextInt(100);
            System.out.println("Random Number: "+num);
            if(num%2==0){
                new Square(num).start();
            }else{
                new Cube(num).start();
            }
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
}

class Square extends Thread{
    int num;
    Square(int num){
        this.num = num;
    }
    public void run(){
        System.out.println("Square of "+num+" = "+num*num);
    }
}

class Cube extends Thread{
    int num;
    Cube(int num){
        this.num = num;
    }
    public void run(){
        System.out.println("Cube of "+num+" = "+num*num*num);
    }
}

public class exp7_1{
    public static void main(String[] args){
        RandomNumberGenerator r = new RandomNumberGenerator();
        r.start();
    }
}
 