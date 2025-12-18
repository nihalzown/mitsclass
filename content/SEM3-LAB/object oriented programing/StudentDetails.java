// Aim: Program to perform Inheritance operations (Student details).
import java.util.*;

class StudentDetailsTask {
    String name;
    int age;
    void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}

class Student extends StudentDetailsTask {
    String grade;

    Student(String n, int a, String g) {
        super.name = n;
        super.age = a;
        this.grade = g;
        super.display();
        System.out.println("Grade: " + grade);
    }
}

public class StudentDetails {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter name and age of student:");
        String n = sc.next();
        int a = sc.nextInt();
        System.out.println("Enter grade of student:");
        String g = sc.next();
        Student s = new Student(n, a, g);
        sc.close();
    }
}