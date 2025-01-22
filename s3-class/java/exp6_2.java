import java.io.FileReader;

import java.util.*;

public class exp6_2{
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("eneter file name with .txt: ");
        String filePath = s.nextLine();
        try {
            FileReader fileReader = new FileReader(filePath);
            int character;
            while ((character = fileReader.read()) != -1) {
                System.out.print((char) character);
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
        s.close();
    }
}