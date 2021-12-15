import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedWriter out = null;
		Socket socket = null;
		Scanner scanner = new Scanner(System.in);
		try {
			socket = new Socket("172.30.1.56", 9999);
			ListeningThread listeningThread = new ListeningThread(socket);
			WritingThread writingThread = new WritingThread(socket);

			listeningThread.run();
			writingThread.run();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				scanner.close();
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				System.out.println("Error is occured");
			}
		}
	}
}