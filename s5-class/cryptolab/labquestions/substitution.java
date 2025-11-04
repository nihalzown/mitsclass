/*a) Write a Java program that implements a simple letter-mapping cipher, 
where each letter of the alphabet is substituted by another based on a given key. 
Use the key where the letter 'A' is replaced by 'Q', 'B' by 'W', and so on. 
Encrypt and then decrypt the phrase "PRIVATE".   */

public class substitution{
    private static String alfa = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String encrypt(String text ,String key){
        text = text.toUpperCase();
        StringBuilder ct = new StringBuilder();
        for(char c : text.toCharArray()){
            int idx = alfa.indexOf(c);
            if(idx!=-1){
                ct.append(key.charAt(idx));
            }
            else{
                    ct.append(c);
            }
        }
        return ct.toString();
    }
    public static String decrypt(String ct ,String key){
        ct.toUpperCase();
        StringBuilder pt = new StringBuilder();
        for(char c : ct.toCharArray()){
            int idx = key.indexOf(c);
            if(idx!=-1){
                pt.append(alfa.charAt(idx));
            }
            else{
                    pt.append(c);
            }
        }
        return pt.toString();
    }
    public static void main(String[] args) {
        String key = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String text = "PRIVATE";
        String monoencrypted = encrypt(text, key);
        String monodecrypted = decrypt(monoencrypted, key);
        System.out.println("Encrypted: " + monoencrypted);
        System.out.println("Decrypted: " + monodecrypted);

    }
}