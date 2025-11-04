import java.util.*;
import java.security.*;

public class md5_dsa {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("enter the message :");
        String text = sc.nextLine();
        MessageDigest m = MessageDigest.getInstance("MD5");
        byte[] digest = m.digest(text.getBytes());
        System.out.println("MD5 :"+Base64.getEncoder().encodeToString(digest));
        StringBuilder hex = new StringBuilder();
        for(byte b : digest){
            String h =String.format("%2x",b);
            hex.append(h);
        }
        System.out.println("hex :"+hex);

        KeyPairGenerator keypgen = KeyPairGenerator.getInstance("DSA");
        keypgen.initialize(2048);
        KeyPair keys = keypgen.generateKeyPair();
        PrivateKey pri = keys.getPrivate();
        PublicKey pub = keys.getPublic();

        System.out.println("DSA Private key :"+ Base64.getEncoder().encodeToString(pri.getEncoded()));
        System.out.println("DSA Public key :"+ Base64.getEncoder().encodeToString(pub.getEncoded()));

        Signature sig = Signature.getInstance("SHA256withDSA");
        sig.initSign(pri);
        sig.update(digest);
        byte[] digital = sig.sign();
        System.out.println("Digital Signature : "+Base64.getEncoder().encodeToString(digital));

        sig.initVerify(pub);
        sig.update(digest);
        boolean isverified = sig.verify(digital);
        System.out.println("Signature verified :"+isverified);

        sc.close();

    }    
}
