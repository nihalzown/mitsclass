import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.Scanner;

public class RSAEncryption {
    public static void main(String[] args) throws Exception {

        // Generate RSA key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // RSA key size (1024, 2048, 4096 bits)
        KeyPair keyPair = keyGen.generateKeyPair();
        
        // Extract public and private keys
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Get user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the message to encrypt: ");
        String message = scanner.nextLine();
        scanner.close();

        // Encrypt using Public Key
        Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypted = encryptCipher.doFinal(message.getBytes("UTF-8"));
        String encText = Base64.getEncoder().encodeToString(encrypted);

        // Decrypt using Private Key
        Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrypted = decryptCipher.doFinal(Base64.getDecoder().decode(encText));
        String decText = new String(decrypted, "UTF-8");

        // Display results
        System.out.println("\n=== RSA Encryption/Decryption Results ===");
        System.out.println("Original : " + message);
        System.out.println("Encrypted: " + encText);
        System.out.println("Decrypted: " + decText);
        
        // Display key information
        System.out.println("\n=== Key Information ===");
        System.out.println("Key Size : " + ((java.security.interfaces.RSAKey) publicKey).getModulus().bitLength() + " bits");
        System.out.println("Public Key  (Base64): " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("Private Key (Base64): " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
    }
}
