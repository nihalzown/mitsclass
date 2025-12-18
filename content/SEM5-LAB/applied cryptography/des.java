// Aim: Program to perform DES encryption (DES Encryption).
import javax.crypto.*;
import java.util.*;

public class des{
    public static void main(String[] args)throws Exception {
        KeyGenerator keygen = KeyGenerator.getInstance("DES");
        SecretKey key = keygen.generateKey();

        Scanner sc = new Scanner(System.in);
        System.out.print("enter text :");
        String msg = sc.nextLine();
        sc.close();

        Cipher cpr = Cipher.getInstance("DES/ECB/PKCS5Padding");
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