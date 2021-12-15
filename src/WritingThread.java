import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class WritingThread implements Runnable {

    BufferedWriter out = null;
    Socket socket = null;
    Scanner scanner = new Scanner(System.in);

    WritingThread (Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                System.out.println("Send >> ");
                String outputMessage = scanner.nextLine();
                if (outputMessage.equalsIgnoreCase("Bye")) {
                    out.write(outputMessage + "\n");
                    out.flush();
                    break;
                }
                out.write(outputMessage + "\n");
                out.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
