package Model;

import Model.ServerAndClient.*;

import java.util.*;

public class ClientHandlerCommands {
    public static Profile sign_in(String username, String password){
        Map<String,Object> tosendserver = new HashMap<>();
        tosendserver .put("command", Command.sign_in);
        tosendserver .put("username",username);
        tosendserver .put("password",password);
        Map<String,Object> recieved = Clientconnection.serve(tosendserver );
        if ( recieved.get("answer") == null ) return null;
        return (Profile) recieved.get("answer");
    }
    public  static Boolean sign_up(Profile profile){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("command", Command.sign_up);
        toSendserver.put("profile", profile);
        Map<String,Object> recieved = Clientconnection.serve(toSendserver);
        if ( recieved.get("answer") == null ) return null;
        return (Boolean) recieved.get("answer");
    }
    public static boolean CheckUsername(String username){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("username",username);
        toSendserver.put("command", Command.Usernameexist);
        Map<String,Object> recieved = Clientconnection.serve(toSendserver);
        return (boolean) recieved.get("answer");
    }
    public static ArrayList<Post>givePostsOfcurrentUser(String username){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("username",username);
        toSendserver.put("command", Command.GetAllposts);
        Map<String,Object> recieved = Clientconnection.serve(toSendserver);
        return (ArrayList<Post>) recieved.get("answer");

    }
    public static Profile get_profile_by_Username(String username){
        Map<String,Object> tosendserver = new HashMap<>();
        tosendserver .put("command", Command.getprofilebyusername);
        tosendserver .put("username",username);
        Map<String,Object> recieved = Clientconnection.serve(tosendserver );
        if ( recieved.get("answer") == null ) return null;
        return (Profile) recieved.get("answer");
    }
    public static Boolean Repost(Post post,String username){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("username",username);
        toSendserver.put("post",post);
        toSendserver.put("command", Command.Repost);
        Map<String,Object> recieved = Clientconnection.serve(toSendserver);
        return (boolean) recieved.get("answer");
    }
    public static Boolean Like(Post post,String username){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("username",username);
        toSendserver.put("post",post);
        toSendserver.put("command", Command.Like);
        Map<String,Object> recieved = Clientconnection.serve(toSendserver);
        return (boolean) recieved.get("answer");
    }

}
