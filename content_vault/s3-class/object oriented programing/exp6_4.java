import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
public class exp6_4 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("enter file name to read with .txt: ");
        String inf= s.nextLine(); 
        System.out.print("enter file name to write with .txt: ");
        String onf= s.nextLine(); 
        try{
            FileInputStream fr = new FileInputStream(inf);
            FileOutputStream fw = new FileOutputStream(onf);
            int data;
            while ((data = fr.read()) != -1) {
                fw.write(data);
            }
            System.out.println("File has been copied successfully.");
            fr.close();
            fw.close();
        } catch (Exception e) {
            System.out.println("An error occurred while processing the file: " + e.getMessage());
        }
        s.close();
    }
}