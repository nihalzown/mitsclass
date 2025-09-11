import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner; // Import the Scanner class

public class des {
    public static void main(String[] args) throws Exception {

        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        SecretKey key = keyGen.generateKey();

        // Use Scanner to get user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the message to encrypt: ");
        String message = scanner.nextLine();
        scanner.close(); // Close the scanner when finished

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(message.getBytes("UTF-8"));
        String encText = Base64.getEncoder().encodeToString(encrypted);

        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encText));
        String decText = new String(decrypted, "UTF-8");

        System.out.println("Original : " + message);
        System.out.println("Encrypted: " + encText);
        System.out.println("Decrypted: " + decText);
    }
}