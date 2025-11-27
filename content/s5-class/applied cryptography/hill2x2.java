/*Write a Java program to implement the 2x2 Hill cipher to encrypt & decrypt 
the message "HOPE" using the key matrix [2 3; 3 5]. */

public class hill2x2{
    private static int[][] keyMatrix = {{2,3},{3,5}};
    public static int modInverse(int a,int m){
        a=a%m;
        for(int x=1;x<m;x++){
            if((a*x)%m==1){
                return x;
            }
        }
        return -1;
    }

    public static String hillencrypt(String plain , int[][] keyMatrix){
        StringBuilder ct = new StringBuilder();
        for(int k=0;k<plain.length();k+=2){
            int[] vector = new int[2];
            vector[0]=plain.charAt(k)-'A';
            vector[1]=plain.charAt(k+1)-'A';

            int[] result = new int[2];
            for(int i =0;i<2;i++){
                result[i]=0;
                for(int j=0;j<2;j++){
                    result[i]+=keyMatrix[i][j]*vector[j];
                }
                result[i]%=26;
            }
            for(int val : result){
                ct.append((char)(val+'A'));
            }

        }return ct.toString();
    }

    public static String hilldecrypt(String cipher , int[][] keyMatrix){
        int det = (keyMatrix[0][0]*keyMatrix[1][1]-keyMatrix[0][1]*keyMatrix[1][0])%26;
        det = ((det%26)+26)%26;

        int detinv = modInverse(det, 26);
        int[][] inv = new int[2][2];

        int[][] adj = new int[2][2];
        adj[0][0] = keyMatrix[1][1];
        adj[0][1] = -keyMatrix[0][1];
        adj[1][0] = -keyMatrix[1][0];
        adj[1][1] = keyMatrix[0][0];

        for(int i=0 ; i<2;i++){
            for(int j=0;j<2;j++){
                inv[i][j]=(adj[i][j]*detinv)%26;
                if(inv[i][j]<0){
                    inv[i][j]+=26;
                }
            }
        }
        StringBuilder pt = new StringBuilder();
        for(int k=0;k<cipher.length();k+=2){
            int[] vector = new int[2];
            vector[0]=cipher.charAt(k)-'A';
            vector[1]=cipher.charAt(k+1)-'A';

            int[] result = new int[2];
            for(int i =0;i<2;i++){
                result[i]=0;
                for(int j=0;j<2;j++){
                    result[i]+=inv[i][j]*vector[j];
                }
                result[i]%=26;
            }
            for(int val : result){
                pt.append((char)(val+'A'));
            }

        }return pt.toString();
    }

    public static void main(String[] args) {
        String msg = "HOPE";
        String encrypted = hillencrypt(msg, keyMatrix);
        String decrypted = hilldecrypt(encrypted, keyMatrix);

        System.out.println("encrypted : "+encrypted);
        System.out.println("decrypted : "+decrypted);
    }
}