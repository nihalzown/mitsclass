import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class des {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter 8-character key: ");
            String key = sc.nextLine();
            if (key.length() > 8) key = key.substring(0, 8);
            while (key.length() < 8) key += " ";

            System.out.print("Enter plaintext: ");
            String plaintext = sc.nextLine();

            String encrypted = encrypt(plaintext, key);
            String decrypted = decrypt(encrypted, key);

            System.out.println("\n--- Results ---");
            System.out.println("Original text: " + plaintext);
            System.out.println("Key used: " + key);
            System.out.println("Encrypted (Base64): " + encrypted);
            System.out.println("Decrypted text: " + decrypted);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }

    public static String encrypt(String plainText, String key) throws Exception {
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String cipherText, String key) throws Exception {
        byte[] encryptedBytes = Base64.getDecoder().decode(cipherText);
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(encryptedBytes);
        return new String(decrypted);
    }
}