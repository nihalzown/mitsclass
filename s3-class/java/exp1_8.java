import java.util.*;
public class exp1_8 {
    public static void main(String[] args) {
        System.out.print("Enter a string: ");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int len = str.length();
        int hl = len/2;
        int c = 0;
        for(int i=0;i<hl;i++)
        {
            if(str.charAt(i)!=str.charAt(len-i-1))
            {
                c++;
            }
        }
        if(c==0)
        {
            System.out.println("The string is a palindrome.");
        }
        else
        {
            System.out.println("The string is not a palindrome.");
        }
        sc.close();
}
}