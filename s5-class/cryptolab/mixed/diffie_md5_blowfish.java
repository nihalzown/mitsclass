/*Write a Java program to implement a secure communication mechanism where:
- sender encrypts the plaintext message using Blowfish  with Diffie Hellman key
- computes the MD5 hash of the original message to ensure integrity. */

import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.*;
import java.math.BigInteger;
import java.security.MessageDigest;

public class diffie_md5_blowfish {
    public static void main(String[] args) throws Exception {
        //diffie hellman
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
            System.out.println("error key mismatch");
            return;
        }

        //blowfish keyy
        byte[] keyBytes = Arrays.copyOf(kA.toByteArray(),16);
        SecretKey key = new SecretKeySpec(keyBytes, "Blowfish");
        
        //blowfish encryption
        System.out.print("Enter message : ");
        sc.nextLine();
        String msg = sc.nextLine();
        sc.close();

        Cipher cpr = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        cpr.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cpr.doFinal(msg.getBytes("UTF-8"));
        String enctext = Base64.getEncoder().encodeToString(encrypted);

        cpr.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cpr.doFinal(Base64.getDecoder().decode(enctext));
        String dectext = new String(decrypted, "UTF-8");

        System.out.println("Shared Secret Key: " + kA);
        System.out.println("Blowfish key : "+Base64.getEncoder().encodeToString(keyBytes));

        System.out.println("Encrypted: " + enctext);
        System.out.println("Decrypted: " + dectext);

        //md5 integrityyyy
        MessageDigest m = MessageDigest.getInstance("MD5");
        byte[] digest = m.digest(msg.getBytes());
        System.out.println("MD5 :"+Base64.getEncoder().encodeToString(digest));
        StringBuilder hex = new StringBuilder();
        for(byte b : digest){
            String h =String.format("%2x",b);
            hex.append(h);
        }
        System.out.println("hex :"+hex);

    }
}
