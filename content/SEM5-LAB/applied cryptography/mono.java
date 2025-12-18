// Aim: Program to perform Monoalphabetic cipher (Monoalphabetic Cipher).
import java.util.*;
import java.util.Scanner;
public class mono {
    private static String alfa = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static String encrypt(String text ,String key){
        text.toUpperCase();
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
    
}
