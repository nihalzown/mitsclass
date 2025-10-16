import javax.crypto.*;
import java.util.*;
import java.math.BigInteger;

public class des_with_diffie {
    public static void main(String[] args) throws Exception {
        // Diffie-Hellman Key Exchange
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
        BigInteger kA = yB.modPow(xA, q); // Secret key
        BigInteger kB = yA.modPow(xB, q); // Secret key verification

        if (!kA.equals(kB)) {
            System.out.println("Error: Keys do not match!");
            return;
        }

        System.out.println("Shared Secret Key: " + kA);
        byte[] keyBytes = Arrays.copyOf(kA.toByteArray(), 8); // Using 8 bytes for DES key
        SecretKey key = new SecretKeySpec(keyBytes, "DES");

        // DES Encryption and Decryption
        System.out.print("Enter text: ");
        sc.nextLine(); // Consume the newline
        String msg = sc.nextLine();
        sc.close();

        Cipher cpr = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cpr.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cpr.doFinal(msg.getBytes("UTF-8"));
        String enctext = Base64.getEncoder().encodeToString(encrypted);

        cpr.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cpr.doFinal(Base64.getDecoder().decode(enctext));
        String dectext = new String(decrypted, "UTF-8");

        System.out.println("Original: " + msg);
        System.out.println("Encrypted: " + enctext);
        System.out.println("Decrypted: " + dectext);
    }
}