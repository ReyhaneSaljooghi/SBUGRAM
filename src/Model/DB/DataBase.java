package Model.DB;

import Model.ServerAndClient.Chat;
import Model.ServerAndClient.Post;
import Model.ServerAndClient.Profile;
import Model.ServerSide.ServerMain;

import java.io.*;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/*this class update the resource file and add all changes
* serverHandlerCommands use the update method whenever it is necessary
* it is singleton because  just one object should exist for server*/
public class DataBase {
    public static final String PROFILE_FILE = "Model/DB/profiles.bin";
    public static final String POSTS_FILE = "Model/DB/posts.bin";
    public static final String CHAT_FILE = "Model/DB/chats.bin";

    private DataBase() {
    }

    public static DataBase dataBase = new DataBase();


    public static DataBase getDataBase() {
        return dataBase;
    }

    public synchronized void loadfirst() {
        File profile_file = new File(PROFILE_FILE);
        File post_file = new File(POSTS_FILE);
        File chat_file=new File(CHAT_FILE);
        if (!profile_file.exists()) {
            try {
                profile_file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            ServerMain.profiles = new ConcurrentHashMap<>((ConcurrentHashMap<String, Profile>) readdb(PROFILE_FILE));
        } catch (ClassNotFoundException e) {

        }catch (NullPointerException e){

        }
        if (!post_file.exists()) {
            try {
                post_file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            ServerMain.AllPosts = new Vector<>((Vector<Post>) readdb(POSTS_FILE));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NullPointerException e){

        }
        if (!chat_file.exists()) {
            try {
                chat_file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            ServerMain.Chats = new Vector<>((Vector<Chat>) readdb(CHAT_FILE));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NullPointerException e){

        }

    }

    public synchronized void updateDB() {
        try {
            writedb(PROFILE_FILE,ServerMain.profiles);
            writedb(POSTS_FILE,ServerMain.AllPosts);
            writedb(CHAT_FILE,ServerMain.Chats);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readdb(String address) throws ClassNotFoundException {
        Object obj = null;
        try (FileInputStream fis = new FileInputStream(address);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            obj = ois.readObject();
            fis.close();
            ois.close();
        } catch (IOException e) {
           // e.printStackTrace();
            //System.out.println("failed to read " + e.getMessage());
        }

        return obj;
    }

    public void writedb(String address, Object obj) throws IOException {
        try (FileOutputStream fout = new FileOutputStream(address);
             ObjectOutputStream oout = new ObjectOutputStream(fout)) {
            oout.writeObject(obj);
            fout.close();
            oout.close();
        } catch (IOException e) {
           // System.out.println("failed to write " + e.getMessage());
        }
    }


}
