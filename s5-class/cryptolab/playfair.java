import java.util.*;

public class playfair {
    private static char[][] matrix = new char[5][5];

    // Prepare key matrix
    private static void generateKeyMatrix(String key) {
        key = key.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder sb = new StringBuilder();
        Set<Character> used = new HashSet<>();
        for (char c : key.toCharArray()) {
            if (!used.contains(c)) {
                sb.append(c);
                used.add(c);
            }
        }
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c != 'J' && !used.contains(c)) {
                sb.append(c);
                used.add(c);
            }
        }
        int k = 0;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                matrix[i][j] = sb.charAt(k++);
    }

    // Find position of character in matrix
    private static int[] findPosition(char c) {
        if (c == 'J') c = 'I';
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == c)
                    return new int[]{i, j};
        return null;
    }

    // Prepare plaintext for encryption
    private static String prepareText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            char a = text.charAt(i);
            char b = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';
            if (a == b) {
                sb.append(a).append('X');
                i++;
            } else {
                sb.append(a).append(b);
                i += 2;
            }
        }
        if (sb.length() % 2 != 0)
            sb.append('X');
        return sb.toString();
    }

    // Encrypt
    public static String encrypt(String key, String plaintext) {
        generateKeyMatrix(key);
        String text = prepareText(plaintext);
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i), b = text.charAt(i + 1);
            int[] posA = findPosition(a), posB = findPosition(b);
            if (posA[0] == posB[0]) { // Same row
                cipher.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                cipher.append(matrix[posB[0]][(posB[1] + 1) % 5]);
            } else if (posA[1] == posB[1]) { // Same column
                cipher.append(matrix[(posA[0] + 1) % 5][posA[1]]);
                cipher.append(matrix[(posB[0] + 1) % 5][posB[1]]);
            } else { // Rectangle
                cipher.append(matrix[posA[0]][posB[1]]);
                cipher.append(matrix[posB[0]][posA[1]]);
            }
        }
        return cipher.toString();
    }

    // Decrypt
    public static String decrypt(String key, String ciphertext) {
        generateKeyMatrix(key);
        StringBuilder plain = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += 2) {
            char a = ciphertext.charAt(i), b = ciphertext.charAt(i + 1);
            int[] posA = findPosition(a), posB = findPosition(b);
            if (posA[0] == posB[0]) { // Same row
                plain.append(matrix[posA[0]][(posA[1] + 4) % 5]);
                plain.append(matrix[posB[0]][(posB[1] + 4) % 5]);
            } else if (posA[1] == posB[1]) { // Same column
                plain.append(matrix[(posA[0] + 4) % 5][posA[1]]);
                plain.append(matrix[(posB[0] + 4) % 5][posB[1]]);
            } else { // Rectangle
                plain.append(matrix[posA[0]][posB[1]]);
                plain.append(matrix[posB[0]][posA[1]]);
            }
        }
        return plain.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter key: ");
        String key = sc.nextLine();
        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();

        String encrypted = encrypt(key, plaintext);
        System.out.println("Encrypted text: " + encrypted);

        String decrypted = decrypt(key, encrypted);
        System.out.println("Decrypted text: " + decrypted);
    }
}