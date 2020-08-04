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
import java.net.*;
import java.io.*;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


public class JokeServer {
    private static final int PORT = 6060;//port
    private static String[] jokes = {"What do you call an overweight chicken? ..ready for dinner",
            "Why did the deer cross the road? ..someone was chasing it",  "what did the teller tell the customer? your check bounced", "knock knock, who is it? ben, ben who? benjamin franklin"};//bank of jokes

    private static String[] proverbs = {"Do onto others as you would have them done onto you",
            "what goes around comes around",  "ask and you shall receive", "the sun will come out tomorrow"};//bank of proverbs

    private  static String[] jokeAbbs= {"JA", "JB", "JC", "JD"};//pre joke abbreviations
    private  static String[] proverbAbbs= {"PA", "PB", "PC", "PD"};//proverb


    //private static int connectionNumber = 0;
    private static HashMap<String, User> userMap;
    public static Mode mode = Mode.JOKE;




    public static void main(String[] args) throws IOException {
        FileWriter fileWriter = new FileWriter("./JokeLog.txt", true);
        ServerSocket ss = new ServerSocket(PORT);//server starts w/only SSocket, then when client tries to connect,
        //below the "socket".accept() code listens for and accepts connection from client
        System.out.println("Gogo's server waiting for connection from client...");
        fileWriter.write("Gogo's server waiting for connection from client...\n");

        userMap = new HashMap<String, User>();


        while(true){
            //System.out.println("Gogo's server waiting for connection from client...");
            Socket client = ss.accept();//accepting connection
            System.out.println("Server connected to client..");
            fileWriter.flush();
            //System.out.println("Waiting on request from client...");
            ClientHandler clientThread = new ClientHandler(client);

            clientThread.start();


        }
    }


    public static String getRandomJoke(int connectionNumber) {
        String joke = jokes[connectionNumber];
        return joke;
    }
    public static String getRandomProverb(int connectionNumber) {
        String proverb = proverbs[connectionNumber];
        return proverb;
    }

    public static String getJokeAbb(int connectionNumber){
        String jokeAbb = jokeAbbs[connectionNumber];
        return jokeAbb;
    }
    public static String getProverbAbb(int connectionNumber){
        String proverbAbb = proverbAbbs[connectionNumber];
        return proverbAbb;
    }
/*
    public static String getServerResponse(String userName){
        int connectionNumber = userConnectionMap.get(userName);
        if(connectionNumber%8 >= 4){
            if(connectionNumber%8==7){
                System.out.println("Proverb Cycle Complete!!!");
            }
            return getRandomProverb(connectionNumber);
        } else{
            if(connectionNumber%8==3){
                System.out.println("Joke Cycle complete!!!");
            }
            return getRandomJoke(connectionNumber);
        }
    }
    public static void incrementUserConnection(String userID){
        System.out.println(userConnectionMap.toString());
        System.out.println("user " + userID + " has submitted " + userConnectionMap.get(userID) + " requests\n");
        if(userConnectionMap.containsKey(userID)){
            int connectionCount = userConnectionMap.get(userID);
            userConnectionMap.put(userID,++connectionCount);
        } else {
            userConnectionMap.put(userID, 0);
        }
    }
*/
    public static User getUser(String userName){
        if(userMap.containsKey(userName)){
            return userMap.get(userName);

        } else {
            User user = new User(userName);
            userMap.put(userName, user);
            return user;
        }
    }
}
