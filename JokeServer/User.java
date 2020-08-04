//eventually settled for User class as a way to handle jokes and proverbs processing/handing

public class User {

    private String userName;
    private int connectionCount;
    private int jokeCount;
    private int proverbCount;
    //use jokeCount and proverbCount to display the order of jokes and or proverbs
    //constructor that takes userName and initializes formerly mentioned vars to 0
    public User(String userName){
        this.userName = userName;

        this.connectionCount = 0;
        this.jokeCount = 0;
        this.proverbCount = 0;
    }

    //initially thought i might need a method to increment connection count but went around it
    public int incrementConnectionCount(){
        this.connectionCount++;
        return this.connectionCount;
    }
    //if joke mode/state, use array index and modulo to alternate between jokes/proverbs
    //build response piece at a time
    public String getUserResponse(){

        if(JokeServer.mode == Mode.PROVERB){
            if(proverbCount%3==0 && proverbCount != 0){
                System.out.println("Proverb Cycle Complete!!!");
            }
            String response = "";
            response += JokeServer.getProverbAbb(proverbCount%4);
            response += " " + this.userName + ": ";
            response += JokeServer.getRandomProverb(proverbCount%4);
            return response;
        } else{
            if(jokeCount%3==0 && jokeCount != 0){
                System.out.println("Joke Cycle complete!!!");
            }
            String response = "";
            response += JokeServer.getJokeAbb(jokeCount%4);
            response += " " + this.userName + ": ";
            response += JokeServer.getRandomJoke(jokeCount%4);
            return response;
        }
    }

    //method to handle user req. incrementing count which is needed to display joke/proverb

    public String handleUserRequest(){
        String response = getUserResponse();
        if(JokeServer.mode == Mode.PROVERB){
            this.proverbCount++;
        } else{
            this.jokeCount++;


        }
        return response;
    }


}


