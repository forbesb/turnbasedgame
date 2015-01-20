package client.login;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.Keymap;
import javax.xml.rpc.ServiceException;

import client.mainClient.backBone.*;
import server.Server;
import server.ServerServiceLocator;

public class Login extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4379276137798299136L;

	//private String url = "http://sxtesta.sys-x.com:8080/BrianSoap/services/BrianSoap";
	public static String url = "http://10.10.10.33/BrianSoap/services/BrianSoap";
	
	private JTextField uname;
	private JPasswordField pword;
	private JButton submit;
	boolean valid = false;
	
	public Login() {
		config();
		setVisible(true);
		setSize(300, 200);
		Container c = new Container();
		c.setLayout(new GridLayout(7,1));
		
		JLabel p = new JLabel("LOGIN");
		p.setHorizontalAlignment(JLabel.CENTER);
		JLabel p2 = new JLabel("Username");
		p2.setHorizontalAlignment(JLabel.CENTER);
		JPanel p3 = new JPanel();
		JLabel p4 = new JLabel("Password");
		p4.setHorizontalAlignment(JLabel.CENTER);
		
		c.add(p);
		
		uname = new JTextField();
		c.add(uname);
		
		c.add(p2);
		c.add(p3);
		c.add(p4);
		
		pword = new JPasswordField();
		Keymap map = pword.getKeymap();
		KeyStroke ks =KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
		map.addActionForKeyStroke(ks, new TestInfo());
		pword.setKeymap(map);
		c.add(pword);
		submit = new JButton("Submit");
		c.add(submit);
		this.add(c);
		submit.addActionListener(this);
		this.requestFocus();
		System.out.println("done...?");
		// TODO Auto-generated constructor stub
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Login l = new Login();
		l.setVisible(true);
		l.setSize(300, 200);
		l.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	
	public static void config(){
		try {
			Hashtable<String, String> configs = new Hashtable<>();
			FileReader f = new FileReader(new File("config.txt"));
			BufferedReader b = new BufferedReader(f);
			String line, key, value;
			while(b.ready()){
				line = b.readLine();
				key = line.substring(0, line.indexOf("="));
				value = line.substring(line.indexOf("=")+1);
				configs.put(key, value);
			}
			b.close();
			boolean sound = Boolean.valueOf(configs.get("sound"));
			if (!sound){
				workers.SoundThread.mute();
			}
			url = configs.get("url");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String testInfo(){
		String k = "failed";
		char[] pass = pword.getPassword();
		String sPass = "";
		for (char c: pass){
			sPass+=c;
		}
		
		byte[] encoded;

		try {
			MessageDigest encoder = MessageDigest.getInstance("SHA-256");
			encoder.update(sPass.getBytes());
			encoded = encoder.digest();
			
			/*So for the hell of it, I might as well try to explain hashes
			 * The idea is they take a string (or list of bytes really) and convert it to a value (SHA-256 basically has no collisions, as in one has never been seen, so they're unique)
			 * The important part of this is that it's basically impossible to convert backwards. Like I don't know if even the NSA (who made this thing) can do it.
			 * So instead of sending a password in plain text over the internet, instead I hash it
			 * get a unique value that the password probably can't be determined from
			 * And once the server has it, I compare it to the (also hashed) value saved for the users password
			 * Since it's unique, if the two are the same then their originals must have also been the same
			 * I know this is probably not needed for a project of this level but I like it and find it cool, so I added
			 * (Also it's not thaaaat great if someone breaks into the database because then they can just get the stored hashed password and inject packets or something)
			 * (But it's cool and better than nothing and I like it ok)
			 */
			
			ServerServiceLocator locator = new ServerServiceLocator();
			Server serv = locator.getBrianSoap(new URL(url));
			
			
			k = serv.login(uname.getText(), encoded);
			System.out.println(k);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return k;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new TestInfo().actionPerformed(e);
		
	}
	
	private void createClient(String k){
		String[] args = new String[1];
		String user = uname.getText();
		args[0]=user;
		Client c = new Client(user,k);
		this.dispose();
		
	}
	
	public void dispose(){
		super.dispose();
		if (!valid){ //only exits if x is pressed, aka no valid input causes it to close
			System.exit(0);
		}
	}
	
	class TestInfo extends AbstractAction{

		private static final long serialVersionUID = 5792649686146236088L;

		@Override
		public void actionPerformed(ActionEvent e) {
			String k = testInfo();
			if (!k.equals("failed")){
				valid=true; //stops the program from exiting on close
				createClient(k);
			} else {
				JFrame parent = new JFrame(); //just so I can use a JOptionPane
				//not a real parent, not even visible
				
				JOptionPane.showMessageDialog(parent, "Login Failed - Incorrect username and password combination.");
				parent.dispose();
			}
		}
		
	}
	
}

