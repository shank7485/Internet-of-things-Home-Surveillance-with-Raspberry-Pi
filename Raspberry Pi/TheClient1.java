import java.io.*;
import java.net.*;

public class TheClient1 {

    public static void main(String[] args) {

        Socket s1 = null;
        String line;

        while (true) {
            try {

                s1 = new Socket("10.176.67.119", 4446);
                BufferedReader BR = new BufferedReader(new InputStreamReader(
                        s1.getInputStream()));
                line = BR.readLine();

                PrintWriter out2 = new PrintWriter("/home/pi/Desktop/command.txt");
                out2.println(line);
                
                System.out.println(line);
                out2.close();
                s1.close();
            }

            catch (Exception e) {

            }

        }

    }

}
