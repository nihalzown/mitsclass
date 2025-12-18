// Aim: Program to perform constructor operations (Box Volume).
public class BoxVolume{
    double width, height, depth;

    public BoxVolume(){
        width = 0;
        height = 0;
        depth = 0;
    }
    public BoxVolume(double w, double h, double d){
        this.width = w;
        this.height = h;
        this.depth = d;
    }
    public BoxVolume(double size){
        this.width = size;
        this.height = size;
        this.depth = size;
    }

    public void volume(){
        double vol = width * height * depth;
        System.out.println("Volume: " +vol);
    }
}

class boxDemo{
    public static void main(String[] args) {
        BoxVolume box1 = new BoxVolume();
        BoxVolume box2 = new BoxVolume(10, 20, 30);
        BoxVolume box3 = new BoxVolume(25);

        System.out.print("Box1 ");
        box1.volume();
        
        System.out.print("Box2 ");
        box2.volume();
        
        System.out.print("Box3 ");
        box3.volume();
    }
}