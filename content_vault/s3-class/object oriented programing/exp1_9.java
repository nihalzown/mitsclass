import java.util.*;
public class exp1_9 {
    public static void main(String[] args) {
        System.out.print("Enter the range: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = 0;
        int b = 1;
        int c = 0;
        System.out.print(a+" "+b+" ");
        for(int i=2;i<n;i++)
        {
            c = a+b;
            System.out.print(c+" ");
            a = b;
            b = c;
        }
        sc.close();
    }
}
