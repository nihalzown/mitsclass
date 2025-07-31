import java.util.*;

public class HillCipher {
    static int mod26(int x) {
        x = x % 26;
        if (x < 0) x += 26;
        return x;
    }

    static int[][] getKeyMatrix(String key) {
        int[][] keyMatrix = new int[2][2];
        keyMatrix[0][0] = (key.charAt(0) - 'A') % 26;
        keyMatrix[0][1] = (key.charAt(1) - 'A') % 26;
        keyMatrix[1][0] = (key.charAt(2) - 'A') % 26;
        keyMatrix[1][1] = (key.charAt(3) - 'A') % 26;
        return keyMatrix;
    }

    static String encrypt(String text, int[][] keyMatrix) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "");
        if (text.length() % 2 != 0) text += "X";
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int[] pair = {text.charAt(i) - 'A', text.charAt(i + 1) - 'A'};
            int c1 = mod26(keyMatrix[0][0] * pair[0] + keyMatrix[0][1] * pair[1]);
            int c2 = mod26(keyMatrix[1][0] * pair[0] + keyMatrix[1][1] * pair[1]);
            cipher.append((char) (c1 + 'A')).append((char) (c2 + 'A'));
        }
        return cipher.toString();
    }

    static int[][] inverseKeyMatrix(int[][] keyMatrix) {
        int det = keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0];
        det = mod26(det);
        int invDet = -1;
        for (int i = 0; i < 26; i++) {
            if (mod26(det * i) == 1) {
                invDet = i;
                break;
            }
        }
        if (invDet == -1) throw new IllegalArgumentException("Key matrix is not invertible.");
        int[][] inv = new int[2][2];
        inv[0][0] = mod26(keyMatrix[1][1] * invDet);
        inv[0][1] = mod26(-keyMatrix[0][1] * invDet);
        inv[1][0] = mod26(-keyMatrix[1][0] * invDet);
        inv[1][1] = mod26(keyMatrix[0][0] * invDet);
        return inv;
    }

    static String decrypt(String cipher, int[][] keyMatrix) {
        int[][] invKey = inverseKeyMatrix(keyMatrix);
        StringBuilder plain = new StringBuilder();
        for (int i = 0; i < cipher.length(); i += 2) {
            int[] pair = {cipher.charAt(i) - 'A', cipher.charAt(i + 1) - 'A'};
            int p1 = mod26(invKey[0][0] * pair[0] + invKey[0][1] * pair[1]);
            int p2 = mod26(invKey[1][0] * pair[0] + invKey[1][1] * pair[1]);
            plain.append((char) (p1 + 'A')).append((char) (p2 + 'A'));
        }
        return plain.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter 4-letter key (A-Z): ");
        String key = sc.nextLine().toUpperCase();
        if (key.length() != 4 || !key.chars().allMatch(Character::isUpperCase)) {
            System.out.println("Invalid key!");
            return;
        }
        int[][] keyMatrix = getKeyMatrix(key);
        System.out.print("Enter plain text: ");
        String plainText = sc.nextLine();
        String encrypted = encrypt(plainText, keyMatrix);
        String decrypted = decrypt(encrypted, keyMatrix);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
        sc.close();
    }
}