package Model.ServerSide;

import Model.ServerAndClient.Command;
import Model.ServerAndClient.Post;
import Model.ServerAndClient.Profile;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;

public class ServerHandlerCommands {
   public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static Map<String,Object> login(Map<String,Object> income){

        String username = (String) income.get("username");
        String password = (String) income.get("password");
        Boolean isNullProfile;
        if(ServerMain.profiles.get(username)==null)
            isNullProfile=true;
        else
            isNullProfile=false;
        Map<String,Object> ans = new HashMap<>();
        ans.put("command", Command.sign_in);
        ans.put("exists",!isNullProfile);
        if(isNullProfile){
            return ans;
        }
        Profile profile = ServerMain.profiles.get(username);
        ans.put("answer",profile);

        if(profile != null){
            System.out.println(profile.getUserName() + " sign in");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            System.out.println("at the time: "+ formatter.format(date));
        }
        return ans;
    }
   public static Map<String,Object> sign_up(Map<String,Object> income){
       Profile newProfile = (Profile) income.get("profile");
       String username = newProfile.getUserName();
       ServerMain.profiles.put(username,newProfile);
       Map<String,Object> ans = new HashMap<>();
       ans.put("command",Command.sign_up);
       ans.put("answer",new Boolean(true));
       System.out.println(newProfile.getUserName() + " sign up and in!");
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
       Date date = new Date();
       System.out.println("at the time: "+ formatter.format(date));
       return ans;

  }
    public static Map<String,Object> CheckUsername(Map<String,Object> income){

        String thisusername= (String) income.get("username");
        Profile profile = ServerMain.profiles.get(thisusername);
        Boolean exist;
        if (profile!=null)
            exist=true;
        else
            exist=false;
        Map<String,Object> ans = new HashMap<>();
        ans.put("answer",exist);
        ans.put("command",Command.Usernameexist);

        return ans;
    }
    public  static Map<String,Object> SendPostsOfcurrentUser(Map<String,Object> income){
        String thisusername= (String) income.get("username");
        Profile profile = ServerMain.profiles.get(thisusername);
        Map<String,Object> ans = new HashMap<>();
        List<Post>CurrentUserposts=new ArrayList<>();
        for(int i=0;i<ServerMain.AllPosts.size();i++){
            if (profile.followings.contains((ServerMain.AllPosts.get(i)).getPublisher())||
                    (ServerMain.AllPosts.get(i)).getPublisher().equals(profile)){
                CurrentUserposts.add(ServerMain.AllPosts.get(i));
            }
        }
        if (CurrentUserposts==null)
            System.out.println("haji nulle ");
        ans.put("answer",CurrentUserposts);
        //ans.put("command",Command.GetAllposts);

        return ans;

    }
    public  static  Map<String,Object> sendprofilebyusername(Map<String,Object> income){
        Map<String,Object> ans = new HashMap<>();
        //ans.put("command", Command.getprofilebyusername);
        String username=(String) income.get("username");
        Profile profile = ServerMain.profiles.get(username);
        ans.put("answer",profile);
        return ans;
    }
    public static Map<String,Object> Repost(Map<String,Object> income){
        Map<String,Object> ans = new HashMap<>();
        String username=(String) income.get("username");
        Profile profile = ServerMain.profiles.get(username);
        Post reposted =new Post();
        reposted=(Post) income.get("post");
        reposted.setWriter(((Post) income.get("post")).getWriter());
        reposted.setCreatedTimeString(new Date());
        reposted.setCreatedTime(Instant.now().toEpochMilli());
        reposted.setPublisher(profile);
        ServerMain.AllPosts.add(reposted);
        ans.put("answer",new Boolean(true));
        System.out.println("action: "+username+" reposted "+reposted.getWriter()+" "+reposted.getTitle());
        System.out.println("at the time: "+ formatter.format(new Date()));
        return ans;
    }
    public static Map<String,Object> Like(Map<String,Object> income) {
        Map<String,Object> ans = new HashMap<>();
        String username=(String) income.get("username");
        Profile profile = ServerMain.profiles.get(username);
        Post liked=(Post) income.get("post");
        liked.Likers.add(profile);
        System.out.println("action: "+username+" liked "+liked.getWriter()+" "+liked.getTitle());
        System.out.println("at the time: "+ formatter.format(new Date()));
        ans.put("answer",new Boolean(true));
        return ans;

    }


}