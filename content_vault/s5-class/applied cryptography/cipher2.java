import java.util.*;

public class cipher2 {

    private static char[][] matrix = new char[5][5];

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

    private static int[] findPosition(char c) {
        if (c == 'J') c = 'I';
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == c)
                    return new int[]{i, j};
        return null;
    }

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

    public static String playfairEncrypt(String text, String key) {
        generateKeyMatrix(key);
        text = prepareText(text);
        StringBuilder cipher = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int[] pos1 = findPosition(text.charAt(i));
            int[] pos2 = findPosition(text.charAt(i + 1));
            if (pos1[0] == pos2[0]) { // Same row
                cipher.append(matrix[pos1[0]][(pos1[1] + 1) % 5]);
                cipher.append(matrix[pos2[0]][(pos2[1] + 1) % 5]);
            } else if (pos1[1] == pos2[1]) { // Same column
                cipher.append(matrix[(pos1[0] + 1) % 5][pos1[1]]);
                cipher.append(matrix[(pos2[0] + 1) % 5][pos2[1]]);
            } else { // Rectangle swap
                cipher.append(matrix[pos1[0]][pos2[1]]);
                cipher.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return cipher.toString();
    }

    public static String playfairDecrypt(String text, String key) {
        generateKeyMatrix(key);
        text = prepareText(text);
        StringBuilder plain = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            int[] pos1 = findPosition(text.charAt(i));
            int[] pos2 = findPosition(text.charAt(i + 1));
            if (pos1[0] == pos2[0]) { // Same row
                plain.append(matrix[pos1[0]][(pos1[1] + 4) % 5]);
                plain.append(matrix[pos2[0]][(pos2[1] + 4) % 5]);
            } else if (pos1[1] == pos2[1]) { // Same column
                plain.append(matrix[(pos1[0] + 4) % 5][pos1[1]]);
                plain.append(matrix[(pos2[0] + 4) % 5][pos2[1]]);
            } else { // Rectangle swap
                plain.append(matrix[pos1[0]][pos2[1]]);
                plain.append(matrix[pos2[0]][pos1[1]]);
            }
        }
        return plain.toString();
    }

    public static String railencrypt(String text, int key) {
        if (key <= 1) return text;
        StringBuilder[] rail = new StringBuilder[key];
        for (int i = 0; i < key; i++) rail[i] = new StringBuilder();

        int dir = 1, row = 0;
        for (char c : text.toCharArray()) {
            rail[row].append(c);
            row += dir;
            if (row == 0 || row == key - 1) dir *= -1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : rail) result.append(sb);
        return result.toString();
    }

    public static String raildecrypt(String cipher, int key) {
        if (key <= 1) return cipher;
        int len = cipher.length();
        boolean[][] mark = new boolean[key][len];

        int dir = 1, row = 0;
        for (int i = 0; i < len; i++) {
            mark[row][i] = true;
            row += dir;
            if (row == 0 || row == key - 1) dir *= -1;
        }

        char[] result = new char[len];
        int idx = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < len; j++) {
                if (mark[i][j] && idx < len) {
                    result[j] = cipher.charAt(idx++);
                }
            }
        }

        StringBuilder plain = new StringBuilder();
        row = 0; dir = 1;
        for (int i = 0; i < len; i++) {
            plain.append(result[i]);
            row += dir;
            if (row == 0 || row == key - 1) dir *= -1;
        }
        return plain.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch;
        do{
            System.out.println("1. playfair cipher\n2. railfence cipher\n3. exit\nchoose an option: \n");
            ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1:
                    System.out.println("Enter key: ");
                    String key = sc.nextLine();
                    System.out.println("Enter plaintext: ");
                    String plaintext = sc.nextLine();
                    String encrypted = playfairEncrypt(plaintext, key);
                    System.out.println("Encrypted text: " + encrypted);
                    String decrypted = playfairDecrypt(encrypted, key);
                    System.out.println("Decrypted text: " + decrypted);
                    break;
            
                case 2:
                    System.out.println("Enter plaintext: ");
                    String railText = sc.nextLine();
                    System.out.println("Enter key: ");
                    int railKey = sc.nextInt();
                    sc.nextLine();
                    String railEncrypted = railencrypt(railText, railKey);
                    System.out.println("Encrypted text: " + railEncrypted);
                    String railDecrypted = raildecrypt(railEncrypted, railKey);
                    System.out.println("Decrypted text: " + railDecrypted);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option.");
                    break;
            }            
        }while (ch!=3);
    }
}
