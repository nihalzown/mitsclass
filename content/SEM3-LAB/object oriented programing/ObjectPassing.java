// Aim: Program to perform Object Passing.
class ObjectPassingTask {
    String name;
    
    ObjectPassingTask(String name) {
        this.name = name;
    }

    static void modify_name(ObjectPassingTask p , String newname) {
        p.name = newname;
    }
}

public class ObjectPassing{
    public static void main(String[] args) {
        ObjectPassingTask p = new ObjectPassingTask("Alice");
        System.out.println("original name :"+p.name);
        ObjectPassingTask.modify_name(p, "Bob");
        System.out.println("new name :"+p.name);
        
    }
}