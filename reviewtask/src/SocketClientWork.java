import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketClientWork {

    //通过套接字socket实现简单得消息接受
    public static void main(String[] args) throws Exception{

        String clientMessage ;//用户输入得信息
        String serverMessage ; //服务器端得信息


        Socket clientSocket = new Socket("127.0.0.0",5557);//发起TCP连接

        BufferedReader fromUser=new BufferedReader(new InputStreamReader(System.in)); //字符读取流，获取从键盘得输入

        BufferedReader fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //获取从服务端得流，建立套接字输入流

        DataOutputStream toServer = new DataOutputStream(clientSocket.getOutputStream()); //建立套接字输出流

        clientMessage = fromUser.readLine();

        toServer.writeBytes(clientMessage);

        serverMessage = fromServer.readLine();

        clientSocket.close();




    }
}
