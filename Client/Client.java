import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    static String strServerIP = "127.0.0.1";
    static int nServerPort = 57000;

    public static void main(String[] args) {
        strServerIP = "127.0.0.1";
        nServerPort = 57000;
        start();
    }

    static private void start() {
        int nDelayTime = 10000;
        InetSocketAddress isaSvr = null;
        Socket socket = null;

        try {
            socket = new Socket();
            System.out.println("서버와 연결을 시도합니다.");

            isaSvr = new InetSocketAddress(strServerIP, nServerPort);
            socket.connect(isaSvr, nDelayTime);
            System.out.printf("연결이 성공 되었습니다.[%s] [%d]\n",strServerIP, nServerPort);

            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();

            byte[] buffers = "0020AAAAAA0123AAAAAA".getBytes(StandardCharsets.UTF_8);

            os.write(buffers);
            os.flush();

            int receiveLength = 20;
            byte receiveByte[] = new byte[receiveLength];
            is.read(receiveByte);
            System.out.println("서버가 되돌려준 메세지: " + new String(receiveByte));


        } catch (Exception e) {
            System.out.println(e);
        }

        if (!socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
   }
}
