// Aim: Program to perform Digital Signature Standard (Digital Signature Standard).
import java.security.*;
import java.util.*;
public class ds {
    public static void main(String[] args) throws Exception{
        KeyPairGenerator keygen = KeyPairGenerator.getInstance("DSA");
        keygen.initialize(2048);
        KeyPair kk = keygen.generateKeyPair();
        PrivateKey priv = kk.getPrivate();
        PublicKey pub = kk.getPublic();

        Scanner sc = new Scanner(System.in);
        System.out.print("enter the text :");
        String text = sc.nextLine();

        Signature sig = Signature.getInstance("SHA256withDSA");
        sig.initSign(priv);
        sig.update(text.getBytes());
        byte[] digital = sig.sign();
        System.out.println("digital signature : "+Base64.getEncoder().encodeToString(digital));

        sig.initVerify(pub);
        sig.update(text.getBytes());
        boolean isVerified = sig.verify(digital);
        System.out.println("signature verified : "+isVerified);
    }
}
