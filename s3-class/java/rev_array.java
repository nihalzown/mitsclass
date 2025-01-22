import java.util.*;
public class rev_array {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[30];
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();
        System.out.print("Enter the elements: ");
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println("Original array: ");
        for(int i=0; i<n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("Reversed array: ");
        for(int i=n-1; i>=0; i--) {
            System.out.print(arr[i] + " ");
        }
    }
}
