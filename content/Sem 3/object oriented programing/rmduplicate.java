import java.util.*;
public class rmduplicate {
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
        for(int i=0; i<n; i++) {
            for(int j=i+1; j<n; j++) {
                if(arr[i] == arr[j]) {
                    for(int k=j; k<n-1; k++) {
                        arr[k] = arr[k+1];
                    }
                    n--;
                    j--;
                }
            }
        }
        System.out.println("Array after removing duplicates: ");
        for(int i=0; i<n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}