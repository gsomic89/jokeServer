import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Socket client;
    //private Boolean mode;
    //private int connectionNumber;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        //this.mode = mode;
        //this.connectionNumber = connectionNumber;

    }


    @Override
    public void run() {
        BufferedReader in = null;
        PrintStream out = null;
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("./JokeLog.txt", true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintStream(client.getOutputStream(), true);
            String userName = in.readLine();
            if(userName.equals("mode")){
                if(JokeServer.mode == Mode.JOKE){
                    JokeServer.mode = Mode.PROVERB;
                } else{
                    JokeServer.mode = Mode.JOKE;
                }
            }
            User user = JokeServer.getUser(userName);


            fileWriter.write("sending client response for user" + userName + "\n");
            out.println(user.handleUserRequest());

        } catch(Exception e) {
            System.err.println(e.getStackTrace());
        } finally {
            try {
                fileWriter.close();
                out.close();
            } catch (IOException e){
                System.err.println(e.getStackTrace());
            }
            try {
                in.close();
            } catch (IOException e) {
                System.err.println(e.getStackTrace());
            }
        }
    }
}
