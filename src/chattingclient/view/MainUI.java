package chattingclient.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainUI extends JFrame {
	Font f1;

	MainUI() {

		f1 = new Font("바탕", Font.PLAIN, 15);

		JLabel lb1 = new JLabel();
		lb1.setBounds(20, 200, 300, 50);
		lb1.setFont(f1);

		JLabel lb2 = new JLabel("Username:");
		lb2.setBounds(20, 50, 80, 30);
		lb2.setFont(f1);

		JTextField tf1 = new JTextField();
		tf1.setBounds(100, 50, 100, 30);
		tf1.setFont(f1);

		JLabel lb3 = new JLabel("Password:");
		lb3.setBounds(20, 100, 80, 30);
		lb3.setFont(f1);

		JPasswordField pw1 = new JPasswordField();
		pw1.setBounds(100, 100, 100, 30);
		pw1.setFont(f1);

		JButton bt1 = new JButton("Login");
		bt1.setBounds(100, 150, 80, 30);
		bt1.setFont(f1);

		bt1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = "Username " + tf1.getText();
				data += ", Password: " + new String(pw1.getPassword());
				lb1.setText(data);
			}
		});

		add(lb1);
		add(pw1);
		add(lb2);
		add(tf1);
		add(lb3);
		add(bt1);
		setTitle("Password EX");
		setSize(320, 320);
		setLayout(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		MainUI boot = new MainUI();
	}
}
