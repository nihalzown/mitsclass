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
    
    public static int modInverse(int a, int m) {
    	a = a % m;
    	for (int x = 1; x < m; x++) {
      		if ((a * x) % m == 1)
        	return x;
    	}
    	return -1;
    }
    
    
    public static String hillEncrypt(String plaintext, int[][] keyMatrix) {
    	plaintext = plaintext.toUpperCase();
    	int[] vector = new int[3];
    	for (int i = 0; i < 3; i++) {
      	vector[i] = plaintext.charAt(i) - 'A';
    	}

    	int[] result = new int[3];
    	for (int i = 0; i < 3; i++) {
      		result[i] = 0;
      		for (int j = 0; j < 3; j++) {
        		result[i] += keyMatrix[i][j] * vector[j];
      		}
      		result[i] %= 26;
    	}

    	StringBuilder encrypted = new StringBuilder();
    	for (int val : result) {
      	encrypted.append((char) (val + 'A'));
    	}
    	return encrypted.toString();
     }
    
    public static int determinant(int[][] m) {
    	return m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1])
        	- m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0])
        	+ m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0]);
    }
    
    public static int[][] adjugate(int[][] m) {
    	int[][] adj = new int[3][3];

    	adj[0][0] = (m[1][1] * m[2][2] - m[1][2] * m[2][1]);
    	adj[0][1] = -(m[1][0] * m[2][2] - m[1][2] * m[2][0]);
    	adj[0][2] = (m[1][0] * m[2][1] - m[1][1] * m[2][0]);

    	adj[1][0] = -(m[0][1] * m[2][2] - m[0][2] * m[2][1]);
    	adj[1][1] = (m[0][0] * m[2][2] - m[0][2] * m[2][0]);
    	adj[1][2] = -(m[0][0] * m[2][1] - m[0][1] * m[2][0]);

    	adj[2][0] = (m[0][1] * m[1][2] - m[0][2] * m[1][1]);
    	adj[2][1] = -(m[0][0] * m[1][2] - m[0][2] * m[1][0]);
    	adj[2][2] = (m[0][0] * m[1][1] - m[0][1] * m[1][0]);

    	int[][] transpose = new int[3][3];
    	for (int i = 0; i < 3; i++)
      		for (int j = 0; j < 3; j++)
        	transpose[i][j] = adj[j][i];

    		return transpose;
    }
  
    public static String hillDecrypt(String ciphertext, int[][] keyMatrix) {
    	int det = determinant(keyMatrix);
    	int detInv = modInverse(det % 26, 26);
    	if (detInv == -1)
      	return "Key not invertible. Decryption failed.";

    	int[][] adj = adjugate(keyMatrix);
    	int[][] inv = new int[3][3];

    	for (int i = 0; i < 3; i++)
      		for (int j = 0; j < 3; j++) {
        		inv[i][j] = (adj[i][j] * detInv) % 26;
        		if (inv[i][j] < 0)
          		inv[i][j] += 26;
      		}

    		ciphertext = ciphertext.toUpperCase();
    		int[] vector = new int[3];
    		for (int i = 0; i < 3; i++) {
      			vector[i] = ciphertext.charAt(i) - 'A';
   		}

    		int[] result = new int[3];
    		for (int i = 0; i < 3; i++) {
      			result[i] = 0;
     			 for (int j = 0; j < 3; j++) {
        			result[i] += inv[i][j] * vector[j];
      			 }
      			 result[i] %= 26;
    		}

    		StringBuilder decrypted = new StringBuilder();
    		for (int val : result) {
      			decrypted.append((char) (val + 'A'));
    		}
    		return decrypted.toString();
  }
    
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch;
        do{
        	System.out.println("\n--- Cipher Menu ---\n1. Caesar Cipher\n2. Substitution Cipher\n3. Hill Cipher \n4. Exit\nChoose option: \n");
      		
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
        			System.out.print("Enter 9-letter key: ");
          			String keyStr = sc.nextLine().toUpperCase();
          			if (keyStr.length() != 9) {
            				System.out.println("Key must be 9 letters.");
            			break;
          			}
          			int[][] matrix = new int[3][3];
          			for (int i = 0; i < 9; i++)
            				matrix[i / 3][i % 3] = keyStr.charAt(i) - 'A';

          			System.out.print("Enter 3-letter plaintext: ");
          			String plain = sc.nextLine().toUpperCase();
          			if (plain.length() != 3) {
            			System.out.println("Plaintext must be 3 letters.");
            			break;
          			}

          			String hillEnc = hillEncrypt(plain, matrix);
          			String hillDec = hillDecrypt(hillEnc, matrix);
          			System.out.println("Encrypted: " + hillEnc);
          			System.out.println("Decrypted: " + hillDec);
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
