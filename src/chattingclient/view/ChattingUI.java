package chattingclient.view;
 
import javax.swing.*; 
import javax.swing.event.*; 
import java.awt.*; 
import java.awt.event.*;
import java.io.*;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.StringTokenizer;
import java.util.Vector;

public class ChattingUI extends JFrame {
	
	//actionListener에서 사용해야해서...
	JFrame internalframe;
	JTextArea txLog = new JTextArea();
	JTextField txID = new JTextField("");
	JTextField txPW = new JTextField("");
	
	MyActionListener myBTListener = new MyActionListener();
	JTextField txChat = new JTextField("");
	
	public ChattingUI(){
		//WindowBuilder로 컴포넌트 생성
		super("Main Frame");
//		setIconImage(Toolkit.getDefaultToolkit().getImage(ChattingUI.class.getResource("/assignment/5982-200.png")));
	    setTitle("chatNow");
	    setSize(500, 500);
	  
	    JMenuBar menuBar = new JMenuBar();
	    setJMenuBar(menuBar);
	      
	    JMenu mnNewMenu = new JMenu("Menu");
	    menuBar.add(mnNewMenu);
	      
	    JMenuItem mntmLogin = new JMenuItem("Login");
	    mnNewMenu.add(mntmLogin);
	      
	    JMenuItem mntmLogout = new JMenuItem("Logout");
	    mnNewMenu.add(mntmLogout);
	    getContentPane().setLayout(null);
	      
	    JButton btnSend = new JButton("Send");
	    btnSend.setBounds(397, 408, 75, 23);
	    getContentPane().add(btnSend);
	      
	    JLabel lblMessages = new JLabel("Messages");
	    lblMessages.setBounds(22, 14, 69, 15);
	    getContentPane().add(lblMessages);
	      
	    JLabel lblMembers = new JLabel("Members");
	    lblMembers.setBounds(338, 14, 57, 15);
	    getContentPane().add(lblMembers);
	      
	    JList list = new JList();
	    list.setBounds(338, 35, 128, 360);
	    getContentPane().add(list);
	      
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(22, 35, 299, 360);
	    getContentPane().add(scrollPane);
	    txLog.setEditable(false);

	    txLog.setLineWrap(true);
	    scrollPane.setViewportView(txLog);

	    JButton btnOK = new JButton("OK");
	    JButton btnCancel = new JButton("Cancel");
	    JLabel lbID = new JLabel("ID");
	    JLabel lbPW = new JLabel("PW");
	    
	    internalframe = new JFrame("Login");
	    Container c= getContentPane();
	    
	    txChat = new JTextField();
	    txChat.setBounds(22, 407, 363, 24);
	    getContentPane().add(txChat);
	    txChat.setColumns(10);
	      
	    Container ic = internalframe.getContentPane();
	    ic.setLayout(new GridLayout(3,2));
	    ic.add(lbID);
	    
	    ic.add(txID);
	    ic.add(lbPW);
	    ic.add(txPW);
	    ic.add(btnOK);
	    ic.add(btnCancel);
	      
	    setVisible(true);
	    setResizable(false);
	    //이벤트가 발생할 버튼들 추가
	    
	    mntmLogin.addActionListener(myBTListener);
	    mntmLogout.addActionListener(myBTListener);
	    btnOK.addActionListener(myBTListener);
	    btnCancel.addActionListener(myBTListener);
	    btnSend.addActionListener(myBTListener);
   }   
	
	public static void main(String[] args) {
		
		ChattingUI a = new ChattingUI();
	}
   
	class MyActionListener implements ActionListener{
   
		public void actionPerformed(ActionEvent e){
			//menuItem들 button으로 cast가 안된다는 에러가 나와서 검색해서 해결
			String name = e.getActionCommand();
			//사용자 ID, PW이 저장된 파일
			File dataFile = new File("D:/login.txt");
			
			String readData;
			StringTokenizer st;
			//ID, PW 빈칸검사를 위해 초기값설정
			String loginID = "";
			String loginPW = "";
	   
			loginID = txID.getText();
			loginPW = txPW.getText();
		   
			if(name.equals("OK")) {
				
				boolean check = false;
				
				txID.setText("");
				txPW.setText(""); 
				
				if(loginID.equals("") || loginPW.equals("")) {//Login frame에서 txID 또는 txPW field가 빈칸이면 
					//ID, PW 확인하라는 MessageDialog 출력
					JOptionPane.showMessageDialog(null,
					"Check your ID, PW please.", "LoginError",
					JOptionPane.ERROR_MESSAGE);
				}
			   
				else {// ID, PW이 빈칸이 아니라면
					
					try {
						//객체생성
						BufferedReader br = new BufferedReader(new FileReader(dataFile));
					   
						while((readData = br.readLine()) != null) {//라인이 null값이 아닐때까지
						   
							st = new StringTokenizer(readData, " ");
						   
							String txtID = st.nextToken();
							String txtPW = st.nextToken();
						   
							if(loginID.equals(txtID) && loginPW.equals(txtPW)) {//입력한 ID txt파일 ID가 같고 입력한PW과 txt파일 PW이 같으면
							   
								check = true;//Login 검증 check값을 True로 바꾸고
								//System.out.println(check);
								break;//while break;
							}
						   
							else {
								
								check = false;// ID, PW이 같지 않다면
							}
						}
					   				
						if(check == true) {// 만약 검증값이 ture이면
							
							txLog.append("> "+ loginID + " is entered this room.\n");//출력
							internalframe.setVisible(false); 						//Loginframe을 숨김
						}
						
						else {//검증값이 false면
							
							txLog.append("> Your Access is denied.\n");//출력
							JOptionPane.showMessageDialog(null,//ID, PW 확인하라는 MessageDialog 출력
							"Check your ID, PW please.", "LoginError",
							JOptionPane.ERROR_MESSAGE);
						}
						
						br.close();// BufferedReader 종료

					}catch(IOException ie){//예외처리
					   
						System.out.println(ie.getMessage());
					}
				}	
			}
            
			if(name.equals("Send")) {
			   
				if(loginID.equals("")) {
				   
					txLog.append("If you want to send a message, You need to login first.\n");
					txChat.setText("");
				}
			   
				else { 
  
					String text = txChat.getText();
					txLog.append("> " + loginID + ": " + text + "\n");
					txChat.setText("");
				}
			}
				   
			if(name.equals("Login")) {
            
				if(internalframe.isVisible()==false){
               
					internalframe.setBounds(150,100, 250,140);
					internalframe.setVisible(true);
				}
			}
			//else if 
			if (name.equals("Cancel")) {
               
				internalframe.setVisible(false);
			}
  
			if (name.equals("Logout")) {
			   
				loginID = txID.getText();
			 
				if(loginID.equals("")) {
				   
					txLog.append("> You need to login.\n");
				}
			   
				else {
				   
					txLog.append("> " + loginID + " is logout this room.\n");	   
					internalframe.setVisible(false);				   
				}
			   
				txID.setText("");
				txPW.setText("");
			}
		}
	} 
}