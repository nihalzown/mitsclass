// Aim: Program to perform Array of Objects.
import java.util.*;
class ArrayOfObjectsTask{
    String name;
    int marks;
    ArrayOfObjectsTask(String name, int marks){
        this.name = name;
        this.marks = marks;
    }
}
public class ArrayOfObjects{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayOfObjectsTask[] s = new ArrayOfObjectsTask[5];
        System.out.println("Enter name and marks of 5 students: ");
        for(int i=0; i<5; i++){
            String name = sc.next();
            int marks = sc.nextInt();
            s[i] = new ArrayOfObjectsTask(name, marks);
        }
        System.out.println("Name and marks of 5 students: ");
        for(int i=0; i<5; i++){
            System.out.println(s[i].name + " " + s[i].marks);
        }
        System.out.println("average marks: ");
        int sum = 0;
        for(int i=0; i<5; i++){
            sum += s[i].marks;
        }
        System.out.println(sum/5);
        sc.close();
    }
}