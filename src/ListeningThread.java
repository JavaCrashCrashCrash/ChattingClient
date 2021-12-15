import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListeningThread implements Runnable {
    Socket socket = null;
    BufferedReader in = null;

    ListeningThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            String inputMessage = null;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                inputMessage = in.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Sever : " + inputMessage);
        }
    }
}
