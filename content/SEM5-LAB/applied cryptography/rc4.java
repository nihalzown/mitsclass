// Aim: Program to perform RC4 encryption (RC4 Encryption).
import javax.crypto.*;
import java.util.*;

public class rc4{
    public static void main(String[] args)throws Exception {
        KeyGenerator keygen = KeyGenerator.getInstance("RC4");
        keygen.init(128);
        SecretKey key = keygen.generateKey();

        Scanner sc = new Scanner(System.in);
        System.out.print("enter text :");
        String msg = sc.nextLine();
        sc.close();

        Cipher cpr = Cipher.getInstance("RC4");
        cpr.init(Cipher.ENCRYPT_MODE,key);
        byte[] encrypted = cpr.doFinal(msg.getBytes("UTF-8"));
        String enctext = Base64.getEncoder().encodeToString(encrypted);

        cpr.init(Cipher.DECRYPT_MODE,key);
        byte[] decrypted = cpr.doFinal(Base64.getDecoder().decode(enctext));
        String dectext = new String(decrypted,"UTF-8");

        System.out.println("original : "+msg);
        System.out.println("encrypted : "+enctext);
        System.out.println("decrypted : "+dectext);
    }
}