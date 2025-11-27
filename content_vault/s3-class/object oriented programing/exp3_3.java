import java.util.*;

class Student {
    String name;
    int rollno;

    void insert(String n, int r) {
        this.name = n;
        this.rollno = r;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Roll No: " + rollno);
    }
}

public class exp3_3 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Student s1 = new Student();
        Student s2 = new Student();
        System.out.println("Enter name and roll no of student 1:");
        String n1 = sc.next();
        int r1 = sc.nextInt();
        s1.insert(n1, r1);
        s1.display();
        System.out.println("Enter name and roll no of student 2:");
        String n2 = sc.next();
        int r2 = sc.nextInt();
        s2.insert(n2, r2);
        s2.display();
        sc.close();
    }
}