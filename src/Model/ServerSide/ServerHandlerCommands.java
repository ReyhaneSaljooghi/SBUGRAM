package Model.ServerSide;

import Model.DB.DataBase;
import Model.ServerAndClient.Command;
import Model.ServerAndClient.Comment;
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
        ans.put("exists",!isNullProfile);
        if(isNullProfile){
            return ans;
        }
        Profile profile = ServerMain.profiles.get(username);
        if (!profile.getPassword().equals(password))
            ans.put("passwordcorrect",false);
        else
            ans.put("passwordcorrect",true);
        ans.put("answer",profile);

        if(profile != null&& profile.getPassword().equals(password)){
            System.out.println(profile.getUserName() + " sign in");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            System.out.println("at the time: "+ formatter.format(date));
        }
        DataBase.getDataBase().updateDB();
        return ans;
    }
   public static Map<String,Object> sign_up(Map<String,Object> income){
       Profile newProfile = (Profile) income.get("profile");
       String username = newProfile.getUserName();
       ServerMain.profiles.put(username,newProfile);
       Map<String,Object> ans = new HashMap<>();
       ans.put("answer",new Boolean(true));
       System.out.println(newProfile.getUserName() + " sign up and in!");
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
       Date date = new Date();
       System.out.println("at the time: "+ formatter.format(date));
       DataBase.getDataBase().updateDB();
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
        DataBase.getDataBase().updateDB();
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
        DataBase.getDataBase().updateDB();

        return ans;

    }
    public  static  Map<String,Object> sendprofilebyusername(Map<String,Object> income){
        Map<String,Object> ans = new HashMap<>();
        String username=(String) income.get("username");
        Profile profile = ServerMain.profiles.get(username);
        ans.put("answer",profile);
        DataBase.getDataBase().updateDB();
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
        DataBase.getDataBase().updateDB();
        return ans;
    }
    public static Map<String,Object> Like(Map<String,Object> income) {
        Map<String,Object> ans = new HashMap<>();
        String username=(String) income.get("username");
        Profile profile = ServerMain.profiles.get(username);
        Post liked=(Post) income.get("post");
        for (int i=0;i<ServerMain.AllPosts.size();i++){
            if(ServerMain.AllPosts.get(i).equals(liked))
                ServerMain.AllPosts.get(i).Likers.add(profile);
        }
        System.out.println(username+" liked "+liked.getWriter()+" "+liked.getTitle());
        System.out.println("at the time: "+ formatter.format(new Date()));
        ans.put("answer",new Boolean(true));
        DataBase.getDataBase().updateDB();
        return ans;

    }
    public static Map<String,Object> toAddComment(Map<String,Object> map){
        Map<String,Object> ans = new HashMap<>();
        Comment comment=(Comment)map.get("comment");
        Post commented=(Post) map.get("post");
        for (int i=0;i<ServerMain.AllPosts.size();i++){
            if(ServerMain.AllPosts.get(i).equals(commented))
                ServerMain.AllPosts.get(i).comments.add(comment);
        }
        System.out.println(comment.getWriter()+" commented "+commented.getTitle());
        System.out.println("at the time: "+ formatter.format(new Date()));
        ans.put("answer",true);
        DataBase.getDataBase().updateDB();
        return ans;

    }

    public static  Map<String,Object> sendAllProfiles(Map<String,Object> map) {
        Map<String,Object> ans = new HashMap<>();
        ArrayList<Profile>tobesend=new ArrayList<>();
      for (Profile profile:ServerMain.profiles.values()){
          tobesend.add(profile);
      }
        ans.put("answer",tobesend);
        return ans;

    }
    public static Map<String,Object> logout(Map<String,Object> map){
        Map<String,Object> ans = new HashMap<>();
        String username=(String) map.get("username");
        System.out.println(username+" log out ");
        System.out.println("at the time: "+ formatter.format(new Date()));
        ans.put("answer",true);
        return ans;
    }
    public static Map<String,Object> Addpost(Map<String,Object> map) {
        Map<String,Object> ans = new HashMap<>();
        String username=(String) map.get("username");
        Profile profile = ServerMain.profiles.get(username);
        Post posted =new Post();
        posted=(Post) map.get("post");
        posted.setCreatedTimeString(new Date());
        posted.setCreatedTime(Instant.now().toEpochMilli());
        ServerMain.AllPosts.add(posted);
        ans.put("answer",true);
        System.out.println("action: "+username+" posted "+ posted.getTitle());
        System.out.println("at the time: "+ formatter.format(new Date()));
        DataBase.getDataBase().updateDB();
        return ans;
    }



}
