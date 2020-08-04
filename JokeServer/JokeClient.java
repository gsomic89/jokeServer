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


//SOME COMMENTS ARE NOT COMMENTS IN SENSE OF "COMMENTS" ABOUT CODE, RATHER SOME ARE
//ALSO THINGS THAT I TRIED AND MODIFIED, CHANGED, ABANDONED, ETC
//SOME WERE REMOVED TO CLEAN UP APPEARANCE OF CODE BUT MOST WAS LEFT.
//PERSONALLY, I FELT LIKE A LOT OF IT IS/WAS RELEVANT TO ME GETTING TO WHATEVER I TURNED IN
public class JokeClient {
    private static final int SERVER_PORT = 6060;//chose over 5000
    private static final String SERVER_IP = "127.0.0.1";//local

    public static void main(String[] args) throws IOException {
        //Socket sock = new Socket(SERVER_IP, SERVER_PORT);


        //store user input into var keyboard
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        //BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        //PrintStream out = new PrintStream(sock.getOutputStream(), true);
        UUID uuid = UUID.randomUUID();//universally unique identifier
        while(true) {
            System.out.println(">>");
            System.out.println("press enter to see a joke or proverb");
            //read user input and store into command
            String command = keyboard.readLine();
            //below, self explanatory really, break if user enters quit and exit
            if(command.equals("quit")) break;
            //create socket
            Socket sock = new Socket(SERVER_IP, SERVER_PORT);
            //out sends msg/req. to server;need to auto flush buffer or use flush()
            //below; send uuid to server
            PrintStream out = new PrintStream(sock.getOutputStream(), true);
            out.println(uuid.toString());
            //read input in from server and save to serverResponse and print to client screen
            BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String serverResponse = in.readLine();
            System.out.println("Server says: " + serverResponse);

            sock.close();
        }
        //sock.close();
        System.exit(0);




    }

}
