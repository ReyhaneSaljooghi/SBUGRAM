package Model.ServerSide;

import Model.DB.DataBase;
import Model.Main;
import Model.ServerAndClient.Post;
import Model.ServerAndClient.Profile;
import javafx.scene.image.Image;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
/*this is the main class in server side so it runs
 and has a while loop to accept the clients.
 it has all profiles and all posts*/

public class ServerMain {
    public static final int PORT = 5056;
    private static boolean isUp = true;

    public static Map<String, Profile> profiles = new ConcurrentHashMap<>();
    public static Vector<Post> AllPosts= new Vector<>();

    public static boolean isServerUp(){
        return isUp;
    }


    public static void main(String[] args) throws IOException {

     DataBase.getDataBase().loadfirst();
     ServerSocket ss = null;
        try {
            ss = new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("waiting for client to connect");
        while (isUp) {
            Socket s = ss.accept();
            System.out.println("client connected");
          new Thread(new ClientHandler(s)).start();
        }

    }
}
