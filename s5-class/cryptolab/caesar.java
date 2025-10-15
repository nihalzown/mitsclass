import java.util.*;
import java.util.Scanner;
public class caesar {
    public static String encrypt(String text , int shift){
        StringBuilder st = new StringBuilder();
        for(char c : text.toCharArray()){
            if(Character.isUpperCase(c)){
                st.append((char)(((c-'A'+shift)%26)+'A'));
            }else if (Character.isLowerCase(c)){
                st.append((char)(((c-'a'+shift)%26)+'a'));
            }else {
                st.append(c);
            }
        }
        return st.toString();
    }
    public static String decrypt(String text , int shift){
        return encrypt(text, 26-(shift%26));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter text :");
        String text = sc.nextLine();
        System.out.print("enter shift :");
        int shift = sc.nextInt();
        String encrypted = encrypt(text,shift);
        String decrypted = decrypt(encrypted, shift);
        System.out.println("encrypted : "+encrypted);
        System.out.println("decrypted : "+decrypted);
        sc.close();

    }
}
