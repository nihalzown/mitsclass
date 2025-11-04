import java.math.BigInteger;
import java.security.SecureRandom;

public class rsa_bigint {
    // RSA key components
    private BigInteger p, q, n, phi, e, d;
    private int bitLength = 1024; // Recommended bit length for security (512 bits for each prime)
    private SecureRandom random;

    public rsa_bigint() {
        random = new SecureRandom();
        
        // RSA Key Generation Algorithm
        
        // Step 1: Generate two large random prime numbers p and q
        p = BigInteger.probablePrime(bitLength / 2, random);
        q = BigInteger.probablePrime(bitLength / 2, random);
        
        // Step 2: Calculate n = p * q (this will be the modulus for both keys)
        n = p.multiply(q);
        
        // Step 3: Calculate Euler's totient function φ(n) = (p-1) * (q-1)
        // This represents the count of integers less than n that are coprime to n
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Step 4: Choose public exponent e such that 1 < e < φ(n) and gcd(e, φ(n)) = 1
        // e must be coprime to φ(n) for the algorithm to work
        e = BigInteger.probablePrime(bitLength / 2, random);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }
        
        // Step 5: Calculate private exponent d, which is the modular inverse of e mod φ(n)
        // This means: e * d ≡ 1 (mod φ(n))
        d = e.modInverse(phi);
    }

    // Public key consists of (e, n)
    public BigInteger getPublicKeyE() { return e; }
    public BigInteger getModulusN() { return n; }
    
    // Private key consists of (d, n)
    public BigInteger getPrivateKeyD() { return d; }

    // RSA Encryption: C = M^e mod n
    // Where C is ciphertext, M is message, e is public exponent, n is modulus
    public byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(e, n).toByteArray();
    }

    // RSA Decryption: M = C^d mod n
    // Where M is original message, C is ciphertext, d is private exponent, n is modulus
    public byte[] decrypt(byte[] encryptedMessage) {
        return (new BigInteger(encryptedMessage)).modPow(d, n).toByteArray();
    }

    public static void main(String[] args) {
        // Create RSA instance with generated keys
        rsa_bigint rsa = new rsa_bigint();
        String originalMessage = "Hello, RSA!";
        System.out.println("Original Message: " + originalMessage);

        // Encrypt the message using public key (e, n)
        byte[] encryptedBytes = rsa.encrypt(originalMessage.getBytes());
        System.out.println("Encrypted Message (bytes): " + new BigInteger(encryptedBytes).toString());

        // Decrypt the message using private key (d, n)
        byte[] decryptedBytes = rsa.decrypt(encryptedBytes);
        String decryptedMessage = new String(decryptedBytes);
        System.out.println("Decrypted Message: " + decryptedMessage);
    }
}