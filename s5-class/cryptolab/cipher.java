import java.util.*;
import java.util.Scanner;

public class cipher {

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for(char c : text.toCharArray()){
            if(Character.isUpperCase(c)){
                result.append((char) (((c-'A'+shift)%26)+'A'));
            }else if(Character.isLowerCase(c)) {
                result.append((char) (((c-'a'+shift)%26)+'a'));
            }else{
                result.append(c);
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text,26-(shift%26));
    }
    
    private static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static String monoencrypt(String plainText, String key) {
        plainText = plainText.toUpperCase();
        StringBuilder cipherText = new StringBuilder();
        for (char c : plainText.toCharArray()) {
            int idx = ALPHABET.indexOf(c);
            if (idx!=-1){
                cipherText.append(key.charAt(idx));
            }else{
                cipherText.append(c);
            }
        }
        return cipherText.toString();
    }

    public static String monodecrypt(String cipherText, String key) {
        cipherText = cipherText.toUpperCase();
        StringBuilder plainText = new StringBuilder();
        for (char c : cipherText.toCharArray()) {
            int idx = key.indexOf(c);
            if(idx != -1){
                plainText.append(ALPHABET.charAt(idx));
            }else{
                plainText.append(c);
            }
        }
        return plainText.toString();
    }
    
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

    static String hillencrypt(String text, int[][] keyMatrix) {
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

    static String hilldecrypt(String cipher, int[][] keyMatrix) {
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
        int ch;
        do{
        	System.out.println("\n--- Cipher Menu ---\n1. Caesar Cipher\n2. Substitution Cipher\n3. Hill Cipher (3x3)\n4. Exit\nChoose option: \n");
      		
      		ch = sc.nextInt();
      		sc.nextLine();
      		switch(ch){
      			case 1 :
      				System.out.print("Enter text: ");
        			String text = sc.nextLine();
        			System.out.print("Enter shift: ");
        			int shift = sc.nextInt();
        			String encrypted = encrypt(text, shift);
        			String decrypted = decrypt(encrypted, shift);
        			System.out.println("Encrypted: " + encrypted);
        			System.out.println("Decrypted: " + decrypted);
        			break;
        		case 2 :
        			System.out.print("Enter key (26 unique uppercase letters): ");
        			String key = sc.nextLine().toUpperCase();
        			System.out.print("Enter plain text: ");
        			String plainText = sc.nextLine();
        			String monoencrypted = monoencrypt(plainText, key);
        			String monodecrypted = monodecrypt(monoencrypted, key);
        			System.out.println("Encrypted: " + monoencrypted);
        			System.out.println("Decrypted: " + monodecrypted);
        			break;
        		case 3 :
                    System.out.print("Enter 4-letter key (A-Z): ");
                    String hillkey = sc.nextLine().toUpperCase();
                    int[][] keyMatrix = getKeyMatrix(hillkey);
                    System.out.print("Enter plain text: ");
                    String hillpt = sc.nextLine();
                    String hillen = hillencrypt(hillpt, keyMatrix);
                    String hillde = hilldecrypt(hillen, keyMatrix);
                    System.out.println("Encrypted: " + hillen);
                    System.out.println("Decrypted: " + hillde);
        			break;
        		case 4:
          			System.out.println("Exiting.");
          			break;

        		default:
          			System.out.println("Invalid option.");
      		}
        }while(ch != 4);
        sc.close();  
    }
}
