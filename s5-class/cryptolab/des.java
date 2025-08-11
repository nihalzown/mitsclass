import java.util.*;

public class des {
    
    // Initial Permutation (IP)
    private static final int[] IP = {
        58, 50, 42, 34, 26, 18, 10, 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
    };

    // Final Permutation (FP)
    private static final int[] FP = {
        40, 8, 48, 16, 56, 24, 64, 32,
        39, 7, 47, 15, 55, 23, 63, 31,
        38, 6, 46, 14, 54, 22, 62, 30,
        37, 5, 45, 13, 53, 21, 61, 29,
        36, 4, 44, 12, 52, 20, 60, 28,
        35, 3, 43, 11, 51, 19, 59, 27,
        34, 2, 42, 10, 50, 18, 58, 26,
        33, 1, 41, 9, 49, 17, 57, 25
    };

    // Expansion function E
    private static final int[] E = {
        32, 1, 2, 3, 4, 5,
        4, 5, 6, 7, 8, 9,
        8, 9, 10, 11, 12, 13,
        12, 13, 14, 15, 16, 17,
        16, 17, 18, 19, 20, 21,
        20, 21, 22, 23, 24, 25,
        24, 25, 26, 27, 28, 29,
        28, 29, 30, 31, 32, 1
    };

    // Permutation P
    private static final int[] P = {
        16, 7, 20, 21, 29, 12, 28, 17,
        1, 15, 23, 26, 5, 18, 31, 10,
        2, 8, 24, 14, 32, 27, 3, 9,
        19, 13, 30, 6, 22, 11, 4, 25
    };

    // S-boxes
    private static final int[][][] S = {
        // S1
        {
            {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
        },
        // S2
        {
            {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
            {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
            {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
            {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
        },
        // S3
        {
            {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
            {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
            {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
            {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
        },
        // S4
        {
            {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
            {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
            {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
            {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
        },
        // S5
        {
            {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
            {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
            {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
            {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
        },
        // S6
        {
            {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
            {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
            {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
            {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
        },
        // S7
        {
            {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
            {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
            {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
            {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
        },
        // S8
        {
            {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
            {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
            {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
            {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
        }
    };

    // Key schedule permutation PC1
    private static final int[] PC1 = {
        57, 49, 41, 33, 25, 17, 9, 1,
        58, 50, 42, 34, 26, 18, 10, 2,
        59, 51, 43, 35, 27, 19, 11, 3,
        60, 52, 44, 36, 63, 55, 47, 39,
        31, 23, 15, 7, 62, 54, 46, 38,
        30, 22, 14, 6, 61, 53, 45, 37,
        29, 21, 13, 5, 28, 20, 12, 4
    };

    // Key schedule permutation PC2
    private static final int[] PC2 = {
        14, 17, 11, 24, 1, 5, 3, 28,
        15, 6, 21, 10, 23, 19, 12, 4,
        26, 8, 16, 7, 27, 20, 13, 2,
        41, 52, 31, 37, 47, 55, 30, 40,
        51, 45, 33, 48, 44, 49, 39, 56,
        34, 53, 46, 42, 50, 36, 29, 32
    };

    // Left shift schedule
    private static final int[] SHIFTS = {1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

    // Convert string to binary
    private static String stringToBinary(String input) {
        StringBuilder binary = new StringBuilder();
        for (char c : input.toCharArray()) {
            binary.append(String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0'));
        }
        return binary.toString();
    }

    // Convert binary to string
    private static String binaryToString(String binary) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteString = binary.substring(i, Math.min(i + 8, binary.length()));
            int ascii = Integer.parseInt(byteString, 2);
            result.append((char) ascii);
        }
        return result.toString();
    }

    // Pad string to multiple of 8
    private static String padString(String input) {
        while (input.length() % 8 != 0) {
            input += " ";
        }
        return input;
    }

    // Permutation function
    private static String permute(String input, int[] table) {
        StringBuilder output = new StringBuilder();
        for (int pos : table) {
            output.append(input.charAt(pos - 1));
        }
        return output.toString();
    }

    // Left shift function
    private static String leftShift(String input, int shifts) {
        return input.substring(shifts) + input.substring(0, shifts);
    }

    // XOR function
    private static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    // S-box substitution
    private static String sBoxSubstitution(String input) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            String chunk = input.substring(i * 6, (i + 1) * 6);
            int row = Integer.parseInt(chunk.charAt(0) + "" + chunk.charAt(5), 2);
            int col = Integer.parseInt(chunk.substring(1, 5), 2);
            int val = S[i][row][col];
            output.append(String.format("%4s", Integer.toBinaryString(val)).replace(' ', '0'));
        }
        return output.toString();
    }

    // Generate round keys
    private static String[] generateKeys(String key) {
        // Convert key to 64-bit binary
        String binaryKey = stringToBinary(key);
        while (binaryKey.length() < 64) {
            binaryKey = "0" + binaryKey;
        }

        // Apply PC1 permutation
        String permutedKey = permute(binaryKey, PC1);
        
        // Split into left and right halves
        String left = permutedKey.substring(0, 28);
        String right = permutedKey.substring(28, 56);

        String[] roundKeys = new String[16];
        
        // Generate 16 round keys
        for (int i = 0; i < 16; i++) {
            // Left shift both halves
            left = leftShift(left, SHIFTS[i]);
            right = leftShift(right, SHIFTS[i]);
            
            // Combine and apply PC2 permutation
            String combined = left + right;
            roundKeys[i] = permute(combined, PC2);
        }
        
        return roundKeys;
    }

    // Feistel function
    private static String feistel(String right, String key) {
        // Expand right half from 32 to 48 bits
        String expanded = permute(right, E);
        
        // XOR with round key
        String xored = xor(expanded, key);
        
        // S-box substitution
        String substituted = sBoxSubstitution(xored);
        
        // Apply permutation P
        return permute(substituted, P);
    }

    // DES encryption/decryption for a single 64-bit block
    private static String desBlock(String block, String[] keys, boolean encrypt) {
        // Apply initial permutation
        String permuted = permute(block, IP);
        
        // Split into left and right halves
        String left = permuted.substring(0, 32);
        String right = permuted.substring(32, 64);

        // 16 rounds of Feistel
        for (int round = 0; round < 16; round++) {
            String tempRight = right;
            String key = encrypt ? keys[round] : keys[15 - round];
            
            // Apply Feistel function
            String feistelResult = feistel(right, key);
            
            // XOR with left half
            right = xor(left, feistelResult);
            left = tempRight;
        }

        // Combine right and left (note the swap)
        String combined = right + left;
        
        // Apply final permutation
        return permute(combined, FP);
    }

    // Main DES encryption function
    public static String encrypt(String plaintext, String key) {
        // Pad plaintext to multiple of 8
        plaintext = padString(plaintext);
        
        // Generate round keys
        String[] keys = generateKeys(key);
        
        StringBuilder ciphertext = new StringBuilder();
        
        // Process each 8-character block
        for (int i = 0; i < plaintext.length(); i += 8) {
            String block = plaintext.substring(i, i + 8);
            String binaryBlock = stringToBinary(block);
            String encryptedBlock = desBlock(binaryBlock, keys, true);
            ciphertext.append(binaryToString(encryptedBlock));
        }
        
        return ciphertext.toString();
    }

    // Main DES decryption function
    public static String decrypt(String ciphertext, String key) {
        // Generate round keys
        String[] keys = generateKeys(key);
        
        StringBuilder plaintext = new StringBuilder();
        
        // Process each 8-character block
        for (int i = 0; i < ciphertext.length(); i += 8) {
            String block = ciphertext.substring(i, i + 8);
            String binaryBlock = stringToBinary(block);
            String decryptedBlock = desBlock(binaryBlock, keys, false);
            plaintext.append(binaryToString(decryptedBlock));
        }
        
        return plaintext.toString().trim();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter 8-character key: ");
        String key = sc.nextLine();
        
        // Ensure key is exactly 8 characters
        if (key.length() > 8) {
            key = key.substring(0, 8);
        } else while (key.length() < 8) {
            key += " ";
        }
        
        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();
        
        // Encrypt the plaintext
        String encrypted = encrypt(plaintext, key);
        
        // Decrypt the same ciphertext
        String decrypted = decrypt(encrypted, key);
        
        // Display results
        System.out.println("\n--- Results ---");
        System.out.println("Original text: " + plaintext);
        System.out.println("Key used: " + key);
        System.out.println("Encrypted text: " + encrypted);
        
        // Show as hex for better readability
        StringBuilder hex = new StringBuilder();
        for (char c : encrypted.toCharArray()) {
            hex.append(String.format("%02X", (int) c));
        }
        System.out.println("Encrypted (hex): " + hex.toString());
        System.out.println("Decrypted text: " + decrypted);
        
        sc.close();
    }
}
