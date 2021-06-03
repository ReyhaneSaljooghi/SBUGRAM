package Model.ServerSide;

import Model.ServerAndClient.Command;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Map;

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
                    case Usernameexist:
                        answer=ServerHandlerCommands.CheckUsername(income);
                        break;
                    case  GetAllposts:
                        answer=ServerHandlerCommands.SendPostsOfcurrentUser(income);
                        break;
                    case getprofilebyusername:
                        answer=ServerHandlerCommands.sendprofilebyusername(income);
                        break;
                    case Repost:
                        answer=ServerHandlerCommands.Repost(income);
                        break;
                    case Like:
                        answer=ServerHandlerCommands.Like(income);
                        break;

                    default:
                        System.out.println("ridi");


                }
                if (answer==null)
                    System.out.println("answer nulle ghable write object");
                socketOut.writeObject(answer);
                socketOut.flush();
            }
            catch(ClassCastException | ClassNotFoundException e){
            }
            catch(IOException e){
                break;
            }

        }
        try{
            socketIn.close();
            socketOut.close();
            userSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}