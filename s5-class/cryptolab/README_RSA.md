# RSA Encryption/Decryption Program

This Java program demonstrates asymmetric encryption and decryption using the RSA algorithm with ECB mode and PKCS1 padding.

## Overview

Unlike the Blowfish program which uses symmetric encryption (same key for encryption and decryption), RSA uses **asymmetric encryption** with a public-private key pair. Data encrypted with the public key can only be decrypted with the corresponding private key.

## Key Differences from Blowfish

| Feature | Blowfish | RSA |
|---------|----------|-----|
| **Type** | Symmetric | Asymmetric |
| **Keys** | Single secret key | Public-private key pair |
| **Speed** | Very fast | Slower |
| **Key Size** | 32-448 bits | 1024-4096 bits |
| **Use Case** | Bulk data encryption | Key exchange, digital signatures |
| **Block Size** | 64 bits | Variable (depends on key size) |

## Features

- **Asymmetric Encryption**: Uses public key for encryption, private key for decryption
- **RSA Algorithm**: Industry-standard public-key cryptosystem
- **2048-bit Keys**: Secure key size for modern applications
- **Base64 Encoding**: Makes binary data readable
- **Key Display**: Shows generated public and private keys

## Line-by-Line Code Explanation

### Import Statements
```java
import javax.crypto.Cipher;              // Encryption/decryption operations
import java.security.KeyPair;            // Container for public-private key pair
import java.security.KeyPairGenerator;   // Generates RSA key pairs
import java.security.PrivateKey;         // RSA private key interface
import java.security.PublicKey;          // RSA public key interface
import java.util.Base64;                 // Base64 encoding/decoding
import java.util.Scanner;                // User input handling
```

### Key Pair Generation (Lines 12-18)
```java
KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
keyGen.initialize(2048);
KeyPair keyPair = keyGen.generateKeyPair();

PublicKey publicKey = keyPair.getPublic();
PrivateKey privateKey = keyPair.getPrivate();
```
- **Line 12**: Creates RSA key pair generator
- **Line 13**: Sets key size to 2048 bits (secure standard)
- **Line 14**: Generates mathematically related public-private key pair
- **Line 17-18**: Extracts individual keys from the pair

### User Input (Lines 21-24)
```java
Scanner scanner = new Scanner(System.in);
System.out.print("Enter the message to encrypt: ");
String message = scanner.nextLine();
scanner.close();
```
Same as Blowfish - collects user message for encryption.

### Encryption with Public Key (Lines 27-30)
```java
Cipher encryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
byte[] encrypted = encryptCipher.doFinal(message.getBytes("UTF-8"));
String encText = Base64.getEncoder().encodeToString(encrypted);
```
- **Line 27**: Creates RSA cipher with PKCS1 padding
- **Line 28**: Initializes cipher for encryption using **public key**
- **Line 29-30**: Encrypts message and converts to Base64

### Decryption with Private Key (Lines 33-36)
```java
Cipher decryptCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
byte[] decrypted = decryptCipher.doFinal(Base64.getDecoder().decode(encText));
String decText = new String(decrypted, "UTF-8");
```
- **Line 34**: Initializes cipher for decryption using **private key**
- **Line 35-36**: Decrypts ciphertext back to original message

### Enhanced Output (Lines 39-46)
```java
System.out.println("\n=== RSA Encryption/Decryption Results ===");
System.out.println("Original : " + message);
System.out.println("Encrypted: " + encText);
System.out.println("Decrypted: " + decText);

System.out.println("\n=== Key Information ===");
System.out.println("Key Size : " + ((java.security.interfaces.RSAKey) publicKey).getModulus().bitLength() + " bits");
System.out.println("Public Key  (Base64): " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
System.out.println("Private Key (Base64): " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
```
- Enhanced output formatting with sections
- Displays actual key size in bits
- Shows Base64-encoded public and private keys

## Technical Details

### RSA Algorithm Specifications
- **Key Size**: 2048 bits (recommended minimum for security)
- **Padding**: PKCS1 (PKCS#1 v1.5 padding)
- **Block Size**: Limited by key size (2048-bit key ≈ 245 bytes max)
- **Security**: Based on difficulty of factoring large numbers

### Security Properties
- **Public Key**: Can be shared openly for encryption
- **Private Key**: Must be kept secret for decryption
- **Non-repudiation**: Only holder of private key can decrypt
- **Perfect for**: Key exchange, digital signatures, small data

## Sample Execution

```
Enter the message to encrypt: Hello RSA Encryption!

=== RSA Encryption/Decryption Results ===
Original : Hello RSA Encryption!
Encrypted: kJ8F2mN9pL6qR3sT7uV1xY4zA9bC5dE8fG0hI2jK3lM6nO7pQ9rS1tU4vW8xY2zA5b
Decrypted: Hello RSA Encryption!

=== Key Information ===
Key Size : 2048 bits
Public Key  (Base64): MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA...
Private Key (Base64): MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC...
```

## RSA vs Blowfish Comparison

### When to Use RSA:
- ✅ Key exchange between parties who have never met
- ✅ Digital signatures and authentication
- ✅ Small amounts of data (certificates, passwords)
- ✅ When you need non-repudiation

### When to Use Blowfish (or AES):
- ✅ Large amounts of data (files, streams)
- ✅ High-speed encryption/decryption
- ✅ When both parties share a secret key
- ✅ Real-time communications

## Security Considerations

### Advantages:
- No need to share secret keys beforehand
- Provides authentication (proves sender identity)
- Suitable for secure key distribution

### Limitations:
- **Slow**: Much slower than symmetric encryption
- **Size Limits**: Can only encrypt small amounts of data
- **Key Size**: Requires large keys for security

## Hybrid Approach (Common in Practice)
Many systems use **both** RSA and symmetric encryption:
1. Generate random symmetric key (AES/Blowfish)
2. Encrypt data with symmetric key (fast)
3. Encrypt symmetric key with RSA public key
4. Send both encrypted data and encrypted key

## Compilation & Execution

```bash
# Compile
javac RSAEncryption.java

# Run
java RSAEncryption
```

## Real-World Applications

- **HTTPS/TLS**: Web security protocols
- **SSH**: Secure shell connections
- **PGP/GPG**: Email encryption
- **Digital Certificates**: Website authentication
- **Cryptocurrency**: Bitcoin transactions

---

**Note**: This program demonstrates RSA concepts for education. Production systems typically use RSA with symmetric encryption for better performance.
