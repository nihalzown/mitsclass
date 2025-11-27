import java.util.*;
class student{
    String name;
    int marks;
    student(String name, int marks){
        this.name = name;
        this.marks = marks;
    }
}
public class exp2_2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        student[] s = new student[5];
        System.out.println("Enter name and marks of 5 students: ");
        for(int i=0; i<5; i++){
            String name = sc.next();
            int marks = sc.nextInt();
            s[i] = new student(name, marks);
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