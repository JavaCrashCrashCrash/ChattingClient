package chattingclient.model;

import java.io.IOException;
import java.net.Socket;

public class Client {
	Socket socket = null;
	public Client() {

	}

	public void connect() {
		try {
			socket = new Socket("localhost", 9999);
			ListeningThread listeningThread = new ListeningThread(socket);
			WritingThread writingThread = new WritingThread(socket);
			System.out.println("Connected!!");
			listeningThread.start();
			writingThread.start();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}