package Model.ServerSide;

import Model.DB.DataBase;
import Model.ServerAndClient.*;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
/*this class contains all methods for handling commands.
ClientHandler use this class.
all methods return a map which contains the information to be sent to the client*/

public class ServerHandlerCommands {
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static Map<String, Object> login(Map<String, Object> income) {

        String username = (String) income.get("username");
        String password = (String) income.get("password");
        Boolean isNullProfile;
        if (ServerMain.profiles.get(username) == null)
            isNullProfile = true;
        else
            isNullProfile = false;
        Map<String, Object> ans = new HashMap<>();
        ans.put("exists", !isNullProfile);
        if (isNullProfile) {
            return ans;
        }
        Profile profile = ServerMain.profiles.get(username);
        if (!profile.getPassword().equals(password))
            ans.put("passwordcorrect", false);
        else
            ans.put("passwordcorrect", true);
        ans.put("answer", profile);

        if (profile != null && profile.getPassword().equals(password)) {
            System.out.println(profile.getUserName() + " sign in");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            System.out.println("at the time: " + formatter.format(date));
        }
        DataBase.getDataBase().updateDB();
        return ans;
    }

    public static Map<String, Object> sign_up(Map<String, Object> income) {
        Profile newProfile = (Profile) income.get("profile");
        String username = newProfile.getUserName();
        ServerMain.profiles.put(username, newProfile);
        Map<String, Object> ans = new HashMap<>();
        ans.put("answer", new Boolean(true));
        System.out.println(newProfile.getUserName() + " sign up and in!");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        System.out.println("at the time: " + formatter.format(date));
        DataBase.getDataBase().updateDB();
        return ans;

    }

    public static Map<String, Object> CheckUsername(Map<String, Object> income) {

        String thisusername = (String) income.get("username");
        Profile profile = ServerMain.profiles.get(thisusername);
        Boolean exist;
        if (profile != null)
            exist = true;
        else
            exist = false;
        Map<String, Object> ans = new HashMap<>();
        ans.put("answer", exist);
        DataBase.getDataBase().updateDB();
        return ans;
    }

    public static Map<String, Object> SendPostsOfcurrentUser(Map<String, Object> income) {
        String thisusername = (String) income.get("username");
        Profile profile = ServerMain.profiles.get(thisusername);
        Map<String, Object> ans = new HashMap<>();
        List<Post> CurrentUserposts = new ArrayList<>();
        for (int i = 0; i < ServerMain.AllPosts.size(); i++) {
            if (profile.followings.contains((ServerMain.AllPosts.get(i)).getPublisher()) ||
                    (ServerMain.AllPosts.get(i)).getPublisher().equals(profile)) {
                if (!profile.mutedUsers.contains(ServerMain.AllPosts.get(i).getPublisher())) {
                    if (!ServerMain.AllPosts.get(i).getPublisher().blockedUsers.contains(profile))
                        CurrentUserposts.add(ServerMain.AllPosts.get(i));
                }

            }
        }
        if (CurrentUserposts == null)
            System.out.println("haji nulle ");
        List<Post> postAnswer = new ArrayList<>();
        for (int i = CurrentUserposts.size() - 1; i >= 0; i--) {
            postAnswer.add(CurrentUserposts.get(i));
        }
        ans.put("answer", postAnswer);
        System.out.println(thisusername + " get posts list ");
        System.out.println("at the time: " + formatter.format(new Date()));
        //DataBase.getDataBase().updateDB();

        return ans;

    }

    public static Map<String, Object> personalPost(Map<String, Object> income) {
        String thisusername = (String) income.get("username");
        Profile profile = ServerMain.profiles.get(thisusername);
        Map<String, Object> ans = new HashMap<>();
        List<Post> CurrentUserposts = new ArrayList<>();
        for (int i = 0; i < ServerMain.AllPosts.size(); i++) {
            if ((ServerMain.AllPosts.get(i)).getPublisher().equals(profile)) {
                CurrentUserposts.add(ServerMain.AllPosts.get(i));
            }
        }
        if (CurrentUserposts == null)
            System.out.println("haji nulle ");
        List<Post> postAnswer = new ArrayList<>();
        for (int i = CurrentUserposts.size() - 1; i >= 0; i--) {
            postAnswer.add(CurrentUserposts.get(i));
        }
        ans.put("answer", postAnswer);
        System.out.println(thisusername + " get posts list ");
        System.out.println("at the time: " + formatter.format(new Date()));

        return ans;

    }

    public static Map<String, Object> sendprofilebyusername(Map<String, Object> income) {
        Map<String, Object> ans = new HashMap<>();
        String username = (String) income.get("username");
        Profile profile = ServerMain.profiles.get(username);
        ans.put("answer", profile);
        DataBase.getDataBase().updateDB();
        System.out.println(username + " get info ");
        System.out.println("at the time: " + formatter.format(new Date()));
        return ans;
    }

    public static Map<String, Object> Repost(Map<String, Object> income) {
        Map<String, Object> ans = new HashMap<>();
        String username = (String) income.get("username");
        Profile profile = ServerMain.profiles.get(username);
        Post reposted = new Post();
        reposted = (Post) income.get("post");
        reposted.setWriter(((Post) income.get("post")).getWriter());
        reposted.setCreatedTimeString(new Date());
        reposted.setCreatedTime(Instant.now().toEpochMilli());
        reposted.setPublisher(profile);
        ServerMain.AllPosts.add(reposted);
        for (Post post : ServerMain.AllPosts) {
            if (post.equals((Post) (Post) income.get("post")))
                post.numberOfReposts++;
        }
        ans.put("answer", new Boolean(true));
        System.out.println("action: " + username + " reposted " + reposted.getWriter() + " " + reposted.getTitle());
        System.out.println("at the time: " + formatter.format(new Date()));
        DataBase.getDataBase().updateDB();
        return ans;
    }

    public static Map<String, Object> Like(Map<String, Object> income) {
        Map<String, Object> ans = new HashMap<>();
        String username = (String) income.get("username");
        Profile profile = ServerMain.profiles.get(username);
        Post liked = (Post) income.get("post");
        for (int i = 0; i < ServerMain.AllPosts.size(); i++) {
            if (ServerMain.AllPosts.get(i).equals(liked))
                ServerMain.AllPosts.get(i).Likers.add(profile);
        }
        System.out.println(username + " liked " + liked.getWriter() + " " + liked.getTitle());
        System.out.println("at the time: " + formatter.format(new Date()));
        ans.put("answer", new Boolean(true));
        DataBase.getDataBase().updateDB();
        return ans;

    }

    public static Map<String, Object> toAddComment(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        Comment comment = (Comment) map.get("comment");
        Post commented = (Post) map.get("post");
        for (int i = 0; i < ServerMain.AllPosts.size(); i++) {
            if (ServerMain.AllPosts.get(i).equals(commented))
                ServerMain.AllPosts.get(i).comments.add(comment);
        }
        System.out.println(comment.getWriter() + " commented " + commented.getTitle());
        System.out.println("at the time: " + formatter.format(new Date()));
        ans.put("answer", true);
        DataBase.getDataBase().updateDB();
        return ans;

    }

    public static Map<String, Object> sendAllProfiles(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        ArrayList<Profile> tobesend = new ArrayList<>();
        for (Profile profile : ServerMain.profiles.values()) {
            tobesend.add(profile);
        }
        ans.put("answer", tobesend);
        return ans;

    }

    public static Map<String, Object> logout(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String username = (String) map.get("username");
        System.out.println(username + " log out ");
        System.out.println("at the time: " + formatter.format(new Date()));
        ans.put("answer", true);
        return ans;
    }

    public static Map<String, Object> Addpost(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String username = (String) map.get("username");
        Profile profile = ServerMain.profiles.get(username);
        Post posted = new Post();
        posted = (Post) map.get("post");
        posted.setPublisher(profile);
        posted.setCreatedTimeString(new Date());
        posted.setCreatedTime(Instant.now().toEpochMilli());
        ServerMain.AllPosts.add(posted);
        ans.put("answer", true);
        System.out.println("action: " + username + " posted " + posted.getTitle());
        System.out.println("at the time: " + formatter.format(new Date()));
        DataBase.getDataBase().updateDB();
        return ans;
    }

    public static Map<String, Object> follow(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String follower = (String) map.get("follower");
        Profile followerProfile = ServerMain.profiles.get(follower);
        String following = (String) map.get("following");
        Profile followingProfile = ServerMain.profiles.get(following);
        followerProfile.followings.add(followingProfile);
        followingProfile.followers.add(followerProfile);
        ans.put("answer", true);
        System.out.println("action: " + followerProfile.getUserName() + " followed " + followingProfile.getUserName());
        System.out.println("at the time: " + formatter.format(new Date()));
        DataBase.getDataBase().updateDB();
        return ans;
    }

    public static Map<String, Object> unfollow(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String follower = (String) map.get("follower");
        Profile followerProfile = ServerMain.profiles.get(follower);
        String following = (String) map.get("following");
        Profile followingProfile = ServerMain.profiles.get(following);
        followerProfile.followings.remove(followingProfile);
        followingProfile.followers.remove(followerProfile);
        ans.put("answer", true);
        System.out.println("action: " + followerProfile.getUserName() + " unfollowed " + followingProfile.getUserName());
        System.out.println("at the time: " + formatter.format(new Date()));
        DataBase.getDataBase().updateDB();
        return ans;
    }

    public static Map<String, Object> update(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String username = (String) map.get("username");
        Profile profile = ServerMain.profiles.get(username);
        String newname = (String) map.get("name");
        String newbirthyear = (String) map.get("birthyear");
        byte[] image = (byte[]) map.get("image");
        profile.setName(newname);
        profile.setBirthYear(newbirthyear);
        if (image!=null) {
            profile.setProfileImage(image);
        }
        for (Post it:ServerMain.AllPosts){
            if (it.getPublisher().equals(profile))
                it.getPublisher().setProfileImage(image);
        }
        System.out.println(image);
        ans.put("answer", profile);
        System.out.println("action: " + username + " update her/his profile ");
        System.out.println("at the time: " + formatter.format(new Date()));
        DataBase.getDataBase().updateDB();
        return ans;

    }

    public static Map<String, Object> deleteAccount(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String username = (String) map.get("username");
        Profile profile = ServerMain.profiles.get(username);

        for (Profile it : ServerMain.profiles.values()) {
            if (it.followings.contains(profile)) {
                it.followings.remove(profile);
            }
            if (it.followers.contains(profile)) {
                it.followers.remove(profile);
            }
        }

        for (int i = ServerMain.AllPosts.size() - 1; i >= 0; i--) {
            if (ServerMain.AllPosts.get(i).publisher.equals(profile))
                ServerMain.AllPosts.remove(i);
        }

        ServerMain.profiles.remove(username);
        ans.put("answer", true);
        System.out.println("action: " + username + " deleted account ");
        System.out.println("at the time: " + formatter.format(new Date()));
        DataBase.getDataBase().updateDB();
        return ans;
    }

    public static Map<String, Object> mute(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String username = (String) map.get("username");
        Profile profile = ServerMain.profiles.get(username);
        Profile muted = ServerMain.profiles.get(map.get("muted"));
        profile.mutedUsers.add(muted);
        System.out.println("action: " + username + " muted " + muted.getUsername());
        System.out.println("at the time: " + formatter.format(new Date()));
        DataBase.getDataBase().updateDB();
        ans.put("answer", true);
        return ans;
    }

    public static Map<String, Object> unMute(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String username = (String) map.get("username");
        Profile profile = ServerMain.profiles.get(username);
        Profile muted = ServerMain.profiles.get(map.get("unmuted"));
        profile.mutedUsers.remove(muted);
        System.out.println("action: " + username + " unmuted " + muted.getUsername());
        System.out.println("at the time: " + formatter.format(new Date()));
        DataBase.getDataBase().updateDB();
        ans.put("answer", true);
        return ans;
    }

    public static Map<String, Object> checkEmail(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String username = (String) map.get("username");
        Profile profile = ServerMain.profiles.get(username);
        String email = (String) map.get("email");
        if (profile.getEmail().equals(email)) {
            ans.put("answer", true);
        } else ans.put("answer", false);
        return ans;
    }

    public static Map<String, Object> block(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String username = (String) map.get("username");
        Profile profile = ServerMain.profiles.get(username);
        Profile blocked = ServerMain.profiles.get(map.get("blocked"));
        profile.blockedUsers.add(blocked);
        System.out.println("action: " + username + "  blocked  " + blocked.getUsername());
        System.out.println("at the time: " + formatter.format(new Date()));
        DataBase.getDataBase().updateDB();
        ans.put("answer", true);
        return ans;
    }

    public static Map<String, Object> unBlock(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String username = (String) map.get("username");
        Profile profile = ServerMain.profiles.get(username);
        Profile unblocked = ServerMain.profiles.get(map
                .get("unblocked"));
        profile.blockedUsers.remove(unblocked);
        System.out.println("action: " + username + " unblocked " + unblocked.getUsername());
        System.out.println("at the time: " + formatter.format(new Date()));
        DataBase.getDataBase().updateDB();
        ans.put("answer", true);
        return ans;
    }

    public static Map<String, Object> sendChat(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String firstperson = (String) map.get("firstPerson");
        String secondperson = (String) map.get("secondPerson");
        Profile profile1 = ServerMain.profiles.get(firstperson);
        Profile profile2 = ServerMain.profiles.get(secondperson);
        Chat answer = null;
        for (int i = 0; i < ServerMain.Chats.size(); i++) {
            System.out.println(ServerMain.Chats.get(i));
            if (ServerMain.Chats.get(i).IsInChat(profile1) && ServerMain.Chats.get(i).getAnother(profile1).equals(profile2)) {
                answer = ServerMain.Chats.get(i);
                System.out.println("chat peyda shod" + ServerMain.Chats.get(i));
                ans.put("answer", answer);
            }
        }
        if (answer == (null)) {
            Chat newChat = new Chat(profile1, profile2);
            answer = newChat;
            System.out.println("new chat built");
            ans.put("answer", answer);
            ServerMain.Chats.add(answer);
        }
//        for (Message it:answer.getMessages()){
//            it.isSeen=true;
//        }
        System.out.println("action: " + firstperson + " get chat with  " + secondperson);
        System.out.println("at the time: " + formatter.format(new Date()));
        DataBase.getDataBase().updateDB();
        return ans;
    }


    public static Map<String, Object> sendAllChat(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String username = (String) map.get("username");
        Profile profile = ServerMain.profiles.get(username);
        ArrayList<Chat> answer = new ArrayList<>();
        for (int i = 0; i < ServerMain.Chats.size(); i++) {
            if (ServerMain.Chats.get(i).IsInChat(profile)) {
                answer.add(ServerMain.Chats.get(i));

            }
        }

        ans.put("answer", answer);
        System.out.println("action: " + username + " get all chats ");
        System.out.println("at the time: " + formatter.format(new Date()));
        return ans;
    }

    public static Map<String, Object> sendMessage(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        Message message = (Message) map.get("message");

        for (int i = 0; i < ServerMain.Chats.size(); i++) {
            if (ServerMain.Chats.get(i).IsInChat(message.getSender()) &&
                    ServerMain.Chats.get(i).getAnother(message.getSender()).equals(message.getReceiver())) {
                ServerMain.Chats.get(i).getMessages().add(message);
                ans.put("answer", ServerMain.Chats.get(i));
                System.out.println("action: " + message.getSender() + " send message to  " + message.getReceiver());
                System.out.println("at the time: " + formatter.format(new Date()));
            }
        }
        DataBase.getDataBase().updateDB();
        return ans;
    }
    public static Map<String, Object> deleteMessage(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        Message message = (Message) map.get("message");
        for (int i=0;i<ServerMain.Chats.size();i++){
            if (ServerMain.Chats.get(i).getMessages().contains(message)){
                ServerMain.Chats.get(i).getMessages().remove(message);
                ans.put("answer", ServerMain.Chats.get(i));
            }
        }
        DataBase.getDataBase().updateDB();
        return ans;
    }

    public static Map<String, Object> editMessage(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        Message message = (Message) map.get("message");
        String text=(String) map.get("text");
        for (int i=0;i<ServerMain.Chats.size();i++){
            if (ServerMain.Chats.get(i).getMessages().contains(message)){
               for (Message it:ServerMain.Chats.get(i).getMessages()) {
                   if(it.equals(message)) {
                       it.setMessage(text);
                       ans.put("answer", ServerMain.Chats.get(i));
                   }
               }
            }
        }
        DataBase.getDataBase().updateDB();
        return ans;
    }
    public static Map<String, Object> setIsSeen(Map<String, Object> map) {
        Map<String, Object> ans = new HashMap<>();
        String firstperson = (String) map.get("firstPerson");
        String secondperson = (String) map.get("secondPerson");
        Profile profile1 = ServerMain.profiles.get(firstperson);
        Profile profile2 = ServerMain.profiles.get(secondperson);
        System.out.println("hey u");

        for (int i = 0; i < ServerMain.Chats.size(); i++) {
            if (ServerMain.Chats.get(i).IsInChat(profile1) &&
                    ServerMain.Chats.get(i).getAnother(profile1).equals(profile2)) {
             ServerMain.Chats.get(i);
             for(Message it:ServerMain.Chats.get(i).getMessages()){
                 if(it.getReceiver().equals(firstperson))
                 it.isSeen=true;
             }
                System.out.println("chat peyda shod" + ServerMain.Chats.get(i));
                ans.put("answer", ServerMain.Chats.get(i));
            }
        }
        DataBase.getDataBase().updateDB();
        return ans;
    }
}
