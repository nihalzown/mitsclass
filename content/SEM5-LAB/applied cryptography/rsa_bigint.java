// Aim: Program to perform RSA encryption (RSA Encryption).
import java.math.BigInteger;
import java.security.SecureRandom;

public class rsa_bigint {

    private BigInteger p, q, n, phi, e, d;
    private int bitLength = 1024; 
    private SecureRandom random;

    public rsa_bigint() {
        random = new SecureRandom();
                
        p = BigInteger.probablePrime(bitLength / 2, random);
        q = BigInteger.probablePrime(bitLength / 2, random);
        
        n = p.multiply(q);
        
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.probablePrime(bitLength / 2, random);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }
        
        d = e.modInverse(phi);
    }

    public BigInteger getPublicKeyE() { return e; }
    public BigInteger getModulusN() { return n; }
    
    public BigInteger getPrivateKeyD() { return d; }

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