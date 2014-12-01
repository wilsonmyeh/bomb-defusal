import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Chat extends JPanel {
	private final int GAME_ID = 4;
	int otherteam;
	BaseClient myClient;
	JTextField input;
	JTextArea output;
	JComboBox targetList; //Who you can message
	public Chat(BaseClient client, int otherteam)
	{
		myClient = client; 
		setPreferredSize(new Dimension(300,500)); 
		this.otherteam = otherteam; 
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
		input = new JTextField(20); 
		output = new JTextArea(100, 20); 
		output.setEditable(false);
		JButton send = new JButton("Send"); 
		send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int sendHere;
            	if(targetList.getSelectedItem().equals("All"))
            	{
            		sendHere = 3; 
            	}
            	else
            	{
            		sendHere = 3-otherteam;  
            	}
 				myClient.sendCommand("4"+sendHere+myClient.myUserName+":"+input.getText()); //4 header indicates chat
 				input.setText(""); 
            }
        });
		JScrollPane display = new JScrollPane(output);
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout()); 
		String[] targets = {"My team", "All"}; 
		targetList = new JComboBox(targets); 
		container.add(input, BorderLayout.CENTER); 
		container.add(send, BorderLayout.EAST); 
		container.add(targetList, BorderLayout.NORTH); 
		
		add(display); 
		add(container); 
		setVisible(true); 
	}
	public void addText(String text)
	{
		output.setText(output.getText()+text+"\n");
		System.out.println("Chat->addText(String) added " + text);
		output.setCaretPosition(output.getDocument().getLength()); 
	}
}
