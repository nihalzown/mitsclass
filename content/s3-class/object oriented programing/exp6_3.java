import java.io.FileWriter;
import java.util.Scanner;
public class exp6_3{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter the file name :");
        String fp = sc.nextLine();
        System.out.println("enter the contend :");
        String con = sc.nextLine();
        try{
            FileWriter fw = new FileWriter(fp);
            fw.write(con);
            System.out.println("success");
            fw.close();
        }catch(Exception e){
            System.out.println("invalid file :"+e.getMessage());
        }
        sc.close();
    }
}
