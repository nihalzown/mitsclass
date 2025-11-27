import java.util.*;
public class exp1_7
{
    public static void main(String args[])
    {
        try (Scanner sc = new Scanner(System.in)) {
          System.out.print("Enter a word : ");
          String str = sc.nextLine();
          int c=0;  
          System.out.print("Enter the letter to be counted : ");  
          String ch = sc.nextLine();      
          for(int i=0 ; i<str.length();i++){
            if(str.charAt(i) == ch.charAt(0)){
              c++;
            }
          }
          System.out.println("The letter "+ch+" is repeated "+c+" times in the word "+str);
        }
    }
} 