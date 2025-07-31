import java.util.*;

public class MonoalphabeticCipher {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String encrypt(String plainText, String key) {
        plainText = plainText.toUpperCase();
        StringBuilder cipherText = new StringBuilder();
        for (char c : plainText.toCharArray()) {
            int idx = ALPHABET.indexOf(c);
            if (idx != -1) {
                cipherText.append(key.charAt(idx));
            } else {
                cipherText.append(c);
            }
        }
        return cipherText.toString();
    }

    public static String decrypt(String cipherText, String key) {
        cipherText = cipherText.toUpperCase();
        StringBuilder plainText = new StringBuilder();
        for (char c : cipherText.toCharArray()) {
            int idx = key.indexOf(c);
            if (idx != -1) {
                plainText.append(ALPHABET.charAt(idx));
            } else {
                plainText.append(c);
            }
        }
        return plainText.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter key (26 unique uppercase letters): ");
        String key = sc.nextLine().toUpperCase();
        if (key.length() != 26 || !key.chars().allMatch(Character::isUpperCase) || key.chars().distinct().count() != 26) {
            System.out.println("Invalid key!");
            return;
        }
        System.out.print("Enter plain text: ");
        String plainText = sc.nextLine();
        String encrypted = encrypt(plainText, key);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        sc.close();
    }
}