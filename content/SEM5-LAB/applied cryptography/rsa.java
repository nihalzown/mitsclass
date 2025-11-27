import javax.crypto.*;
import java.util.*;
import java.security.*;

public class rsa{
    public static void main(String[] args)throws Exception {
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
        keygen.initialize(2048);
        KeyPair keyp = keygen.generateKeyPair();
        PublicKey pub = keyp.getPublic();
        PrivateKey priv = keyp.getPrivate();

        Scanner sc = new Scanner(System.in);
        System.out.print("enter text :");
        String msg = sc.nextLine();
        sc.close();

        Cipher cpr = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cpr.init(Cipher.ENCRYPT_MODE,pub);
        byte[] encrypted = cpr.doFinal(msg.getBytes("UTF-8"));
        String enctext = Base64.getEncoder().encodeToString(encrypted);

        cpr.init(Cipher.DECRYPT_MODE,priv);
        byte[] decrypted = cpr.doFinal(Base64.getDecoder().decode(enctext));
        String dectext = new String(decrypted,"UTF-8");

        System.out.println("original : "+msg);
        System.out.println("encrypted : "+enctext);
        System.out.println("decrypted : "+dectext);
    }
}