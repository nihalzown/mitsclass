import java.util.*;
public class exp1_6
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a word");
        String str = sc.nextLine();
        String rev = "";
        for(int i = str.length()-1; i>=0; i--)
        {
            rev = rev + str.charAt(i);
        }
        System.out.println("Reverse of the word is: "+rev);
        sc.close();
    }
}
