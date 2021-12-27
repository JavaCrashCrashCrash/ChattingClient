package chattingclient.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ListeningThread extends Thread {
    Socket socket = null;
    BufferedReader in = null;

    ListeningThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
            String inputMessage = null;
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (true) {
                    inputMessage = in.readLine();
                    System.out.println("Server : " + inputMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
