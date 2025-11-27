import java.util.Scanner;
public class Box{
    double width;
    double height;
    double depth;
    public Box(double w, double h, double d){
        width = w;
        height = h;
        depth =  d;
    }

public double volume(){
    return width*height*depth;
}
    public static void main(String args[]){
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter the width of the box : ");
            double w = sc.nextDouble();
            System.out.print("Enter the height of the box : ");
            double h = sc.nextDouble();
            System.out.print("Enter the depth of the box : ");
            double d = sc.nextDouble();
            Box b = new Box(w,h,d);
            System.out.println("The volume of the box is : "+b.volume());
        }
    }
}
