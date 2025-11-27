public class railfence {
    public static String encrypt(String text, int key) {
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

    public static String decrypt(String cipher, int key) {
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
        String text = "WEAREDISCOVEREDFLEEATONCE";
        int key = 3;
        String encrypted = encrypt(text, key);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
    }
}
