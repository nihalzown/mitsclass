import java.util.Scanner;
class amstrong
{
    public static void main(String []args)
    {
        Scanner sc=new Scanner(System.in) ;
        System.out.println("Enter a number: ");
        int n=sc.nextInt();
        int temp=n;
        int sum=0;
        while(n>0)
        {
            int r=n%10;
            sum=sum+r*r*r;
            n=n/10;
            
        }
        if(temp==sum)
        {
            System.out.println("Amstrong number");
        }
        else
        {
            System.out.println("Not an amstrong number");
        }
        sc.close();
    }
}
