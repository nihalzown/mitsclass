  class exp2_3{
    double width, height, depth;

    public exp2_3(){
        width = 0;
        height = 0;
        depth = 0;
    }
    public exp2_3(double w, double h, double d){
        this.width = w;
        this.height = h;
        this.depth = d;
    }
    public exp2_3(double size){
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
        exp2_3 box1 = new exp2_3();
        exp2_3 box2 = new exp2_3(10, 20, 30);
        exp2_3 box3 = new exp2_3(25);

        System.out.print("Box1 ");
        box1.volume();
        
        System.out.print("Box2 ");
        box2.volume();
        
        System.out.print("Box3 ");
        box3.volume();
    }
}