/*Implement a Java program where two parties use Diffie-Hellman to compute a shared secret key. 
Print the shared key from both parties to verify correctness. */
import java.util.*;
import java.math.BigInteger;
public class diffie {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a prime number (q): ");
        BigInteger q = sc.nextBigInteger();
        System.out.print("Enter a primitive root :");
        BigInteger a = sc.nextBigInteger();
        System.out.print("Enter private key for user A :");
        BigInteger xA = sc.nextBigInteger();
        System.out.print("Enter private key for user B :");
        BigInteger xB = sc.nextBigInteger();
        BigInteger yA = a.modPow(xA, q);
        BigInteger yB = a.modPow(xB, q);
        System.out.println("Public key for user A : " + yA);
        System.out.println("Public key for user B : " + yB);
        BigInteger kA = yB.modPow(xA, q);
        BigInteger kB = yA.modPow(xB, q);
        System.out.println("Secret key for user A : " + kA);
        System.out.println("Secret key for user B : " + kB);
        sc.close();
    }
}
