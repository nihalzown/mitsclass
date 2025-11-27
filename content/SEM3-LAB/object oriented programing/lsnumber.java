import java.util.*;
public class lsnumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr[] = new int[30];
        System.out.print("Enter the number of elements: ");
        int n = sc.nextInt();
        System.out.print("Enter the elements: ");
        for(int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        int largest = arr[0];
        int smallest = arr[0];
        for(int i=0; i<n; i++) {
            if(arr[i] > largest) {
                largest = arr[i];
            }
            if(arr[i] < smallest) {
                smallest = arr[i];
            }
        }
        System.out.println("Largest number: " + largest);
        System.out.println("Smallest number: " + smallest);
    }
}
