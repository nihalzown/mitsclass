//diffie key and des . key printed also.

import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.math.BigInteger;

public class des_with_diffie{
    public static void main(String[] args) throws Exception{
        //diffie-hellman

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a prime number (q): ");
        BigInteger q = sc.nextBigInteger();
        System.out.print("Enter a primitive root: ");
        BigInteger a = sc.nextBigInteger();
        System.out.print("Enter private key for user A: ");
        BigInteger xA = sc.nextBigInteger();
        System.out.print("Enter private key for user B: ");
        BigInteger xB = sc.nextBigInteger();
        BigInteger yA = a.modPow(xA, q);
        BigInteger yB = a.modPow(xB, q);
        BigInteger kA = yB.modPow(xA, q);
        BigInteger kB = yA.modPow(xB, q);

        if(!kA.equals(kB)){
            System.out.println("Error: Keys do not match!");
            return;
        }

        //des key ondaki.

        System.out.println("Shared secret key : "+kA);
        byte[] keyBytes = Arrays.copyOf(kA.toByteArray(),8);
        SecretKey key = new SecretKeySpec(keyBytes, "DES");

        //des algorithm

        System.out.print("Enter text: ");
        sc.nextLine();
        String msg = sc.nextLine();
        sc.close();

        System.out.println("DES key : "+Base64.getEncoder().encodeToString(keyBytes));

        Cipher cpr = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cpr.init(Cipher.ENCRYPT_MODE,key);
        byte[] encrypted = cpr.doFinal(msg.getBytes("UTF-8"));
        String enctext = Base64.getEncoder().encodeToString(encrypted);

        cpr.init(Cipher.DECRYPT_MODE,key);
        byte[] decrypted = cpr.doFinal(Base64.getDecoder().decode(enctext));
        String dectext = new String(decrypted,"UTF-8");

        System.out.println("encrypted : "+enctext);
        System.out.println("decrypted : "+dectext);
    }
}