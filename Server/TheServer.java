import java.net.*;
import java.io.*;

public class TheServer {

    public static void main(String[] args) {

        Socket s0 = null;
        Socket s1 = null;
        ServerSocket ss0 = null;
        ServerSocket ss1 = null;
        String line;

        while (true) {

            try {
                ss0 = new ServerSocket(4445);
                ss1 = new ServerSocket(4446);
            }

            catch (Exception E) {
                System.out.println("Can't listen to ports");
            }

            try {

                s0 = ss0.accept();

                System.out.println("Connection from Phone");

                s1 = ss1.accept();

                System.out.println("Connection from Client");

                BufferedReader BR = new BufferedReader(new InputStreamReader(
                        s0.getInputStream()));
                line = BR.readLine();

                System.out.println("Command got from phone is :" + line);

                PrintStream PS = new PrintStream(s1.getOutputStream());
                PS.print(line);

                s0.close();
                s1.close();
                ss0.close();
                ss1.close();

                BR.close();
                PS.close();

            }

            catch (Exception e) {
                System.out.println("Some IO or Network Error");
            }
        }
    }
}
