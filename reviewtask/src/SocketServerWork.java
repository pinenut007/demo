import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerWork {


    //服务端

    public static void main(String[] args) throws Exception{

        String clientMessage;
        String serverMessage;

        ServerSocket serverSocket = new ServerSocket(5557);

        System.out.println("正在监听5557端口");

        while(true){
            Socket collection = serverSocket.accept();//调用accept(),建立TCP连接
            DataInputStream fromClient = new DataInputStream(collection.getInputStream());

            DataOutputStream toClient = new DataOutputStream(collection.getOutputStream());

            clientMessage = fromClient.readUTF();
            System.out.println("成功建立TCP连接");

            System.out.println(clientMessage);



        }
    }
}
