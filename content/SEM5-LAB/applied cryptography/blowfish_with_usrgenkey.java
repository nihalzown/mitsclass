// Aim: Program to perform Blowfish encryption with user-generated key (Blowfish with User-Generated Key).
import javax.crypto.spec.*;
import javax.crypto.*;
import java.util.*;

public class blowfish_with_usrgenkey {
    public static void main(String[] args)throws Exception {
        Scanner sc = new Scanner(System.in);
        String text = "INFORMATION";
        System.out.println("msg given :"+text);
        System.out.print("Enter the Key :");
        String usrkey = sc.nextLine();

        byte[] keyBytes = Arrays.copyOf(usrkey.getBytes("UTF-8"),16);
        SecretKey key = new SecretKeySpec(keyBytes, "Blowfish");

        Cipher cpr = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        cpr.init(Cipher.ENCRYPT_MODE,key);
        byte[] encrypted = cpr.doFinal(text.getBytes("UTF-8"));
        String enctext = Base64.getEncoder().encodeToString(encrypted);

        cpr.init(Cipher.DECRYPT_MODE,key);
        byte[] decrypted = cpr.doFinal(Base64.getDecoder().decode(enctext));
        String dectext = new String(decrypted,"UTF-8");

        System.out.println("Blowfish Key : "+Base64.getEncoder().encodeToString(keyBytes));
        System.out.println("Encrypted : "+enctext);
        System.out.println("Decrypted : "+dectext);

    }
}
