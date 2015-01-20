package client.login;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.Keymap;
import javax.xml.rpc.ServiceException;

import server.Server;
import server.ServerServiceLocator;

public class CreateAccount extends JDialog implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5794846260102573271L;
	private String url = "http://10.10.10.33/BrianSoap/services/BrianSoap";
	private JTextField uname;
	private JPasswordField pword;
	private JButton submit;
	boolean valid = false;
	
	public CreateAccount() {
		Login.config();
		url = Login.url;
		
		setVisible(true);
		setSize(300, 200);
		Container c = new Container();
		c.setLayout(new GridLayout(7,1));
		
		JLabel p = new JLabel("Create Account");
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
		map.addActionForKeyStroke(ks, new TestInfo(this));
		pword.setKeymap(map);
		c.add(pword);
		submit = new JButton("Submit");
		c.add(submit);
		this.add(c);
		submit.addActionListener(this);
		this.requestFocus();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateAccount l = new CreateAccount();
		l.setVisible(true);
		l.setSize(300, 200);
		l.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	public boolean createAcc(){
		char[] pass = pword.getPassword();
		String sPass = "";
		for (char c: pass){
			sPass+=c;
		}
		
		boolean created = false;
		
		MessageDigest encoder;
		try {
			encoder = MessageDigest.getInstance("SHA-256");
			encoder.update(sPass.getBytes());
			byte[] encoded = encoder.digest();
			
			ServerServiceLocator locator = new ServerServiceLocator();
			Server serv = locator.getBrianSoap(new URL(url));
			
			
			boolean k = serv.createAccount(uname.getText(), encoded);
			System.out.println(k);
			created = k;
			//verified = k;
			
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
		
		
		return created;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		new TestInfo(this).actionPerformed(e);	
	}
	
	class TestInfo extends AbstractAction{
		/**
		 * 
		 */
		private static final long serialVersionUID = 2080770446977560630L;
		private JDialog root;
		public TestInfo(JDialog root){
			this.root = root;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			boolean success = createAcc();
			if (success){
				JOptionPane.showMessageDialog(root, "Account Created!");
				root.dispose();
			} else {
				JOptionPane.showMessageDialog(root, "Username already used");
			}
		}
		
	}

}
