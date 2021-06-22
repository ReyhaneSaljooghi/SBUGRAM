package Model;

import Model.ServerAndClient.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
/*this class contains all methods to send the command to the server and receive the answer
* the return types are  different  but all the information is in map*/
public class ClientHandlerCommands {
    public static Profile sign_in(String username, String password){
        Map<String,Object> tosendserver = new HashMap<>();
        tosendserver .put("command", Command.sign_in);
        tosendserver .put("username",username);
        tosendserver .put("password",password);
        Map<String,Object> recieved = Clientconnection.serve(tosendserver );
        if (!(Boolean)recieved.get("exists"))
            return null;
        if (!(boolean) recieved.get("passwordcorrect"))
            return null;
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
        toSendserver.put("command", Command.UsernameExist);
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
        tosendserver .put("command", Command.getProfileByUsername);
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
    public static boolean addcomment(Comment comment,Post post){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("command", Command.AddComment);
        toSendserver.put("comment",comment);
        toSendserver.put("post",post);
        Map<String,Object> recieved = Clientconnection.serve(toSendserver);
        return (boolean) recieved.get("answer");
    }
    public static ArrayList<Profile>getProfiles(){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("command", Command.GetAllProfiles);
        Map<String,Object> recieved = Clientconnection.serve(toSendserver);

        return (ArrayList<Profile>) (recieved.get("answer"));
    }
    public static boolean logout(String username){
        Map<String,Object> toSendserver = new HashMap<>();
       toSendserver.put("username",username);
       toSendserver.put("command",Command.LogOut);
        Map<String,Object> recieved = Clientconnection.serve(toSendserver);
        return (boolean) recieved.get("answer");
    }
    public static boolean Addpost(Post post,String username){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("username",username);
        toSendserver.put("post",post);
        toSendserver.put("command",Command.AddPost);
        Map<String,Object> recieved = Clientconnection.serve(toSendserver);
        return (boolean) recieved.get("answer");
    }
    public static boolean follow(String follower,String following){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("follower",follower);
        toSendserver.put("following",following);
        toSendserver.put("command",Command.Follow);
        Map<String,Object> recieved = Clientconnection.serve(toSendserver);
        return (boolean) recieved.get("answer");
    }

    public static boolean unfollow(String follower,String following){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("follower",follower);
        toSendserver.put("following",following);
        toSendserver.put("command",Command.UnFollow);
        Map<String,Object> recieved = Clientconnection.serve(toSendserver);
        return (boolean) recieved.get("answer");
    }
    public static Profile update(String name,String birthyear,String userame){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("name",name);
        toSendserver.put("username",userame);
        toSendserver.put("birthyear",birthyear);
        toSendserver.put("command",Command.Update);
        Map<String,Object> recieved = Clientconnection.serve(toSendserver);
        Profile p= (Profile) recieved.get("answer");
        return (Profile) recieved.get("answer");

    }

    public static boolean deleteAccount(String username){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("username",username);
        toSendserver.put("command",Command.DeleteAccount);

        Map<String,Object> received = Clientconnection.serve(toSendserver);

        return (boolean) received.get("answer");

    }

    public static boolean mute(String username,String muted){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("username",username);
        toSendserver.put("command",Command.Mute);
        toSendserver.put("muted",muted);
        Map<String,Object> received = Clientconnection.serve(toSendserver);
        return (boolean) received.get("answer");

    }
    public static boolean unMute(String username,String muted){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("username",username);
        toSendserver.put("command",Command.UnMute);
        toSendserver.put("unmuted",muted);
        Map<String,Object> received = Clientconnection.serve(toSendserver);
        return (boolean) received.get("answer");

    }

    public static boolean getPass(String username,String email){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("username",username);
        toSendserver.put("command",Command.GetPassword);
        toSendserver.put("email",email);
        Map<String,Object> received = Clientconnection.serve(toSendserver);
        return (boolean) received.get("answer");

    }

    public static boolean block(String username,String blocked){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("username",username);
        toSendserver.put("command",Command.Block);
        toSendserver.put("blocked",blocked);
        Map<String,Object> received = Clientconnection.serve(toSendserver);
        return (boolean) received.get("answer");

    }
    public static boolean unBlock(String username,String unblocked){
        Map<String,Object> toSendserver = new HashMap<>();
        toSendserver.put("username",username);
        toSendserver.put("command",Command.UnBlock);
        toSendserver.put("unblocked",unblocked);
        Map<String,Object> received = Clientconnection.serve(toSendserver);
        return (boolean) received.get("answer");

    }


}
