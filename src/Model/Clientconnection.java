package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Map;

/*this class send and receive the information */
public class Clientconnection
{
    public static String serverAddress="localhost";
    public static final int PORT = 5056;
    static boolean isConnected = false;
    public static Socket socket;
    public static ObjectInputStream socketInput;
    public static ObjectOutputStream socketOutput;


    public static boolean isConnected(){
        return isConnected;
    }

    public static Boolean connectToServer(){
        if(socket != null) return false;
        try{
            socket = new Socket( serverAddress, PORT);
            socketOutput = new ObjectOutputStream( socket.getOutputStream() );
            socketInput = new ObjectInputStream( socket.getInputStream() );
            isConnected = true;
            return true;

        }catch (ConnectException e){
        }
        catch (IOException e) {
        }
        return false;
    }

    //asli
    public static Map<String,Object> serve(Map<String,Object> toSend){
        Map<String,Object> recieved = null;
        try{
            socketOutput.writeObject(toSend);
            socketOutput.flush();
            socketOutput.reset();
            recieved = (Map<String,Object>) socketInput.readObject();
            return recieved;

        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch( IOException e){
            e.printStackTrace();
        }
        return recieved;
    }
    public static void disconnectFromServer() {

            try {
                socketInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socketOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            isConnected = false;
       socket = null;
       socketInput = null;
        socketOutput = null;
    }
}
