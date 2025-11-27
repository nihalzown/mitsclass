import java.util.Scanner;
public class exp1_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of rows of the first matrix: ");
        int r1 = sc.nextInt();
        System.out.print("Enter the number of columns of the first matrix: ");
        int c1 = sc.nextInt();
        System.out.print("Enter the number of rows of the second matrix: ");
        int r2 = sc.nextInt();
        System.out.print("Enter the number of columns of the second matrix: ");
        int c2 = sc.nextInt();
        if(c1!=r2)
        {
            System.out.println("The matrices cannot be multiplied with each other.");
        }
        else
        {
            int a[][] = new int[r1][c1];
            int b[][] = new int[r2][c2];
            int mul[][] = new int[r1][c2];
            System.out.println("Enter the elements of the first matrix: ");
            for(int i=0;i<r1;i++)
            {
                for(int j=0;j<c1;j++)
                {
                    a[i][j] = sc.nextInt();
                }
            }
            System.out.println("Enter the elements of the second matrix: ");
            for(int i=0;i<r2;i++)
            {
                for(int j=0;j<c2;j++)
                {
                    b[i][j] = sc.nextInt();
                }
            }
            for(int i=0;i<r1;i++)
            {
                for(int j=0;j<c2;j++)
                {
                    for(int k=0;k<c1;k++)
                    {
                        mul[i][j] += a[i][k]*b[k][j];
                    }
                }
            }
            System.out.println("The product of the two matrices is: ");
            for(int i=0;i<r1;i++)
            {
                for(int j=0;j<c2;j++)
                {
                    System.out.print(mul[i][j]+" ");
                }
                System.out.println();
            }
        }
        sc.close();
    }
}
