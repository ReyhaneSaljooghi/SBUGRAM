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
        Profile check=new Profile("reyhane");
        check.setPassword("1212");
        profiles.put("reyhane",check);
        Post p1=new Post();
        p1.setTitle("hi first one");
        p1.setWriter("reyhane");
        Post p2=new Post();
        p2.setTitle("hi second one");
        p2.setWriter("mamad");
        Post p3=new Post();
        p3.setTitle("hi third one");
        p3.setWriter("akbar");
        p1.setCreatedTimeString(new Date());
        p2.setCreatedTimeString(new Date());
        p3.setCreatedTimeString(new Date());
        AllPosts.add(p1);
        AllPosts.add(p2);
        AllPosts.add(p3);
        Profile following1=new Profile("mamad");
        following1.setName("mamadali");
        check.followings.add(following1);
        Profile notfollowing=new Profile("akbar");
        p1.setPublisher(check);
        p2.setPublisher(following1);
        p3.setPublisher(notfollowing);
        File file=new File("C:\\Users\\Saljooghi\\Desktop\\Project\\images\\earthstar.jpg");
        File file2=new File("C:\\Users\\Saljooghi\\Desktop\\Project\\images\\simple.jpg");
        check.setProfileImage(Files.readAllBytes(file.toPath()));
        following1.setProfileImage(Files.readAllBytes(file2.toPath()));


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
