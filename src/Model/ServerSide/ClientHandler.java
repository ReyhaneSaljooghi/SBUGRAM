package Model.ServerSide;
import Model.ServerAndClient.Command;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

/*This class is thread so it implements runnable.
 it handles the commands and
  finally sends the result to the client*/

public class ClientHandler implements Runnable {
    private Socket userSocket;
    private ObjectOutputStream socketOut;
    private ObjectInputStream socketIn;
    public Boolean clientOnline = true;


    public ClientHandler(Socket socket){
        try{
            userSocket = socket;
            this.socketIn = new ObjectInputStream(userSocket.getInputStream() );
            this.socketOut = new ObjectOutputStream(userSocket.getOutputStream() );
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        while(clientOnline){
            Map<String,Object> income = null;

            try{
                income = (Map<String,Object>) socketIn.readObject();
                Map<String,Object> answer = null;
                Command command = (Command) income.get("command");
                switch(command){
                    case sign_in:
                        answer = ServerHandlerCommands.login(income);
                        break;
                    case sign_up:
                      answer=ServerHandlerCommands.sign_up(income);
                      break;
                    case UsernameExist:
                        answer=ServerHandlerCommands.CheckUsername(income);
                        break;
                    case  GetAllposts:
                        answer=ServerHandlerCommands.SendPostsOfcurrentUser(income);
                        break;
                    case getProfileByUsername:
                        answer=ServerHandlerCommands.sendprofilebyusername(income);
                        break;
                    case Repost:
                        answer=ServerHandlerCommands.Repost(income);
                        break;
                    case Like:
                        answer=ServerHandlerCommands.Like(income);
                        break;
                    case AddComment:
                        answer=ServerHandlerCommands.toAddComment(income);
                        break;
                    case GetAllProfiles:
                        answer=ServerHandlerCommands.sendAllProfiles(income);
                        break;
                    case LogOut:
                        answer=ServerHandlerCommands.logout(income);
                        break;
                    case AddPost:
                        answer=ServerHandlerCommands.Addpost(income);
                        break;
                    case Follow:
                        answer=ServerHandlerCommands.follow(income);
                        break;
                    case UnFollow:
                        answer=ServerHandlerCommands.unfollow(income);
                        break;
                    case Update:
                        answer=ServerHandlerCommands.update(income);
                        break;
                    case DeleteAccount:
                        answer=ServerHandlerCommands.deleteAccount(income);
                        break;
                    case Mute:
                        answer=ServerHandlerCommands.mute(income);
                        break;
                    case UnMute:
                        answer=ServerHandlerCommands.unMute(income);
                        break;

                    default:
                        System.out.println("default");


                }
                if (answer==null)
                    System.out.println("answer is null before write object");
                socketOut.reset();
                socketOut.writeObject(answer);
                socketOut.flush();
            }
            catch(ClassCastException e) {

            }catch (ClassNotFoundException e){

            }
            catch(IOException e){
               break;
            }

        }

    }
}
