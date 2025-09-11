import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class rc4demo {
    public static void main(String[] args) throws Exception {
        // Generate a random RC4 key
        KeyGenerator keyGen = KeyGenerator.getInstance("RC4");
        keyGen.init(128); // RC4 supports key sizes from 40 to 2048 bits, 128 is common
        SecretKey secretKey = keyGen.generateKey();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the message to encrypt: ");
        String text = scanner.nextLine();
        scanner.close();

        // Encrypt using RC4
        Cipher cipher = Cipher.getInstance("RC4");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        String encText = Base64.getEncoder().encodeToString(encrypted);

        // Decrypt using RC4
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encText));
        String decText = new String(decrypted, "UTF-8");

        System.out.println("Original : " + text);
        System.out.println("Encrypted: " + encText);
        System.out.println("Decrypted: " + decText);
    }
}