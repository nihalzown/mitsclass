/*Create a Caesar cipher in Java with a shifting 
key of 3 to encrypt and 
decrypt the message "CRYPTOGRAPHY ".   */
import java.util.*;
public class caesar {
    public static String encrypt(String text,int shift){
        StringBuilder st = new StringBuilder();
        for(char c : text.toCharArray()){
            if(Character.isUpperCase(c)){
                st.append((char)(((c-'A'+shift)%26)+'A'));
            }else if(Character.isLowerCase(c)){
                st.append((char)(((c-'a'+shift)%26)+'a'));
            }else{
                st.append(c);
            }
        }
        return st.toString();
    }
    public static String decrypt(String text,int shift){
        return encrypt(text, 26-(shift%26));
    }
    public static void main(String[] args) {
        String text = "CRYPTOGRAPHY";
        int shift = 3;
        String encrypted = encrypt(text,shift);
        String decrypted = decrypt(encrypted, shift);
        System.out.println("encrypted : "+encrypted);
        System.out.println("decrypted : "+decrypted);
    }
}
