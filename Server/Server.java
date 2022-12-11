import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    static String strServerListenIP = "0.0.0.0";
    static int nServerListenPort = 57000;
    public static void main(String[] args) throws IOException {
        strServerListenIP = "";
        nServerListenPort = 57000;
        start();
    }

    static private void start() {

        ServerSocket serverSocket = null;
        InetSocketAddress isa = null;

        try {
            serverSocket = new ServerSocket();
            isa = new InetSocketAddress(strServerListenIP, nServerListenPort);

            serverSocket.bind(isa);

            Socket socket = serverSocket.accept();
            isa = (InetSocketAddress) socket.getRemoteSocketAddress();
            System.out.printf("요청한 연결을 확인함 :  [%s] [%d]\n " , isa.getHostName(),nServerListenPort);

            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();

            int receiveLength = 20;
            byte receiveByte[] = new byte[receiveLength];
            is.read(receiveByte);

            os.write(receiveByte);
            os.flush();



        } catch (Exception e) {
            System.out.println(e);
        }

        if (!serverSocket.isClosed()) {
            try {
                serverSocket.close();
            } catch ( IOException e) {
                System.out.println(e);
            }
        }
    }
}
