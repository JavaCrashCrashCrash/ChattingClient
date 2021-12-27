package chattingclient.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainUI extends JFrame {

	MainUI() {
		setTitle("Chatting Client");
		setBounds(400, 500, 800, 500);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, 800, 500);
		JTextField chatField = new JTextField();
		JTextArea chatArea = new JTextArea();
		chatArea.setBounds(20, 520, 700, 100);
		JScrollPane scroll = new JScrollPane(chatArea);
		scroll.setBounds(20, 20, 700, 300);
		mainPanel.add(scroll);
		mainPanel.add(chatArea);
		add(mainPanel);
	}

	public static void main(String[] args) {
		MainUI boot = new MainUI();
	}
}
