import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class blowfish {
    public static void main(String[] args) throws Exception {

        // Generate a Blowfish key
        KeyGenerator keyGen = KeyGenerator.getInstance("Blowfish");
        keyGen.init(128); // Blowfish supports key sizes from 32 bits to 448 bits (use 128 bits here)
        SecretKey key = keyGen.generateKey();

        // Get user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the message to encrypt: ");
        String message = scanner.nextLine();
        scanner.close();

        // Encrypt
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(message.getBytes("UTF-8"));
        String encText = Base64.getEncoder().encodeToString(encrypted);

        // Decrypt
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encText));
        String decText = new String(decrypted, "UTF-8");

        System.out.println("Original : " + message);
        System.out.println("Encrypted: " + encText);
        System.out.println("Decrypted: " + decText);
    }
}