# Blowfish Encryption/Decryption Program

This Java program demonstrates symmetric encryption and decryption using the Blowfish algorithm with ECB mode and PKCS5 padding.

## Overview

The program generates a random Blowfish key, encrypts user input, and then decrypts it back to verify the process. Blowfish is a symmetric block cipher designed by Bruce Schneier in 1993, known for its speed and effectiveness.

## Features

- **Symmetric Encryption**: Uses the same key for both encryption and decryption
- **Blowfish Algorithm**: Fast, secure block cipher with variable key length
- **Base64 Encoding**: Converts binary encrypted data to readable text format
- **UTF-8 Support**: Properly handles international characters
- **User Interactive**: Prompts user for input message

## Code Structure

### Import Dependencies
```java
import javax.crypto.Cipher;           // Core encryption/decryption functionality
import javax.crypto.KeyGenerator;     // Generates cryptographic keys
import javax.crypto.SecretKey;        // Interface for symmetric keys
import java.util.Base64;              // Base64 encoding/decoding
import java.util.Scanner;             // User input handling
```

### Class Declaration
```java
public class blowfish {
```
Main class containing the encryption/decryption logic.

## Line-by-Line Code Explanation

### 1. Key Generation (Lines 12-14)
```java
KeyGenerator keyGen = KeyGenerator.getInstance("Blowfish");
keyGen.init(128);
SecretKey key = keyGen.generateKey();
```
- **Line 12**: Creates a KeyGenerator instance specifically for the Blowfish algorithm
- **Line 13**: Initializes the generator with 128-bit key size (Blowfish supports 32-448 bits)
- **Line 14**: Generates a cryptographically secure random key

### 2. User Input Collection (Lines 17-20)
```java
Scanner scanner = new Scanner(System.in);
System.out.print("Enter the message to encrypt: ");
String message = scanner.nextLine();
scanner.close();
```
- **Line 17**: Creates Scanner object to read from standard input (console)
- **Line 18**: Displays prompt asking user for message to encrypt
- **Line 19**: Reads entire line of user input including spaces
- **Line 20**: Closes scanner to prevent resource leaks

### 3. Encryption Process (Lines 23-26)
```java
Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
cipher.init(Cipher.ENCRYPT_MODE, key);
byte[] encrypted = cipher.doFinal(message.getBytes("UTF-8"));
String encText = Base64.getEncoder().encodeToString(encrypted);
```
- **Line 23**: Creates Cipher with three components:
  - `Blowfish`: The encryption algorithm
  - `ECB`: Electronic Codebook mode (each block encrypted independently)
  - `PKCS5Padding`: Adds padding to make message fit block size requirements
- **Line 24**: Initializes cipher in encryption mode using the generated secret key
- **Line 25**: Converts message to UTF-8 bytes and encrypts, returning byte array
- **Line 26**: Encodes encrypted bytes to Base64 string for readable display

### 4. Decryption Process (Lines 29-31)
```java
cipher.init(Cipher.DECRYPT_MODE, key);
byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encText));
String decText = new String(decrypted, "UTF-8");
```
- **Line 29**: Re-initializes same cipher instance in decryption mode with same key
- **Line 30**: Decodes Base64 string to bytes, then decrypts to original byte array
- **Line 31**: Converts decrypted bytes back to UTF-8 string

### 5. Output Display (Lines 33-35)
```java
System.out.println("Original : " + message);
System.out.println("Encrypted: " + encText);
System.out.println("Decrypted: " + decText);
```
- **Line 33**: Shows the original user-entered message
- **Line 34**: Displays encrypted text in Base64 format
- **Line 35**: Shows decrypted result (should match original)

## Technical Details

### Blowfish Algorithm Specifications
- **Block Size**: 64 bits (8 bytes)
- **Key Size**: Variable from 32 to 448 bits (4 to 56 bytes)
- **Structure**: Feistel network with 16 rounds
- **Speed**: Very fast encryption/decryption

### Cipher Configuration
- **Algorithm**: Blowfish
- **Mode**: ECB (Electronic Codebook)
- **Padding**: PKCS5Padding

### ECB Mode Characteristics
- Each plaintext block is encrypted independently
- Same plaintext block always produces same ciphertext block
- No initialization vector (IV) required
- **Security Note**: Not recommended for production due to pattern visibility

## Sample Execution

```
Enter the message to encrypt: Hello Cryptography!
Original : Hello Cryptography!
Encrypted: 8B7C9D2E1F3A4B5C6D7E8F9A0B1C2D3E4F5A6B7C
Decrypted: Hello Cryptography!
```

## Security Considerations

### Strengths
- Blowfish is cryptographically secure for most applications
- Fast encryption/decryption performance
- No known practical attacks on the algorithm itself

### Weaknesses & Recommendations
- **ECB Mode**: Reveals patterns in data - use CBC or GCM mode for production
- **Key Management**: Keys should be securely stored, not generated randomly each time
- **Random Keys**: Each execution generates new key - encrypted data can't be decrypted later

## Improvements for Production Use

1. **Use CBC or GCM Mode**: 
   ```java
   Cipher cipher = Cipher.getInstance("Blowfish/CBC/PKCS5Padding");
   ```

2. **Persistent Key Storage**: Save/load keys securely instead of generating new ones

3. **Initialization Vector**: Use random IV for CBC mode

4. **Key Derivation**: Derive keys from passwords using PBKDF2

5. **Error Handling**: Add specific exception handling for crypto operations

## Compilation & Execution

```bash
# Compile
javac blowfish.java

# Run
java blowfish
```

## Dependencies

- Java Cryptography Extension (JCE) - included in standard Java runtime
- No external libraries required

---

**Note**: This program is for educational purposes to demonstrate Blowfish encryption concepts. For production applications, consider additional security measures and use more secure cipher modes.
