/*--------------------------------------------------------

1. Name / Date: GORAN SOMIC 1/26/2020

2. Java version used, if not the official version for the class:CLASS VERSION

3. Precise command-line compilation examples / instructions:

> javac JokeServer.java
>java JokeServer
//repeat for JokeServer,JokeClient,JokeClientAdmin


4. Precise examples / instructions to run this program:

In separate shell windows:

> java JokeServer
> java JokeClient
> java JokeClientAdmin

All acceptable commands are displayed on the various consoles.

This runs across machines, in which case you have to pass the IP address of
the server to the clients. For exmaple, if the server is running at
140.192.1.22 then you would type:

> java JokeClient 140.192.1.22
> java JokeClientAdmin 140.192.1.22

5. List of files needed for running the program.

 a. JokeServer.java
 b. JokeClient.java
 c. JokeClientAdmin.java

5. Notes:

e.g.:
*********FOR SOME REASON, ONE TIME, WHEN I HIT ---ENTER--- KEY, THE JOKE AND OR PROVERB DRAGS,******
*********TO DISPLAY; IF YOU HIT ENTER TWICE IT SEEMS TO WORK******

----------------------------------------------------------*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.UUID;

public class JokeClientAdmin {
    private static final int SERVER_PORT = 6060;
    private static final String SERVER_IP = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        //Socket sock = new Socket(SERVER_IP, SERVER_PORT);



        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println(">>");
            System.out.println("press enter to swap server mode");

            String command = keyboard.readLine();

            if(command.equals("quit")) break;
            Socket sock = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("connected to server...");
            PrintStream out = new PrintStream(sock.getOutputStream(), true);
            out.println("mode");

            sock.close();
        }
        //sock.close();
        System.exit(0);




    }

}
