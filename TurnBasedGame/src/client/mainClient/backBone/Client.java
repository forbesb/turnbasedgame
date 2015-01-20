package client.mainClient.backBone;
//Hongyu Wang
//ICS4U
//Jan 4th, 2015
//JClient

//Imports
import javax.swing.*;
import javax.xml.rpc.ServiceException;



import client.login.Login;
import client.mainClient.profile.Profile;
import server.Server;
import server.ServerImplementation;
import server.ServerServiceLocator;
import server.beans.Team;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

@SuppressWarnings("serial")
public class Client extends JFrame{
	private static final boolean PROPERBUTTON = true;
	
	public static User user = new User("Darknal43", new String[]{"Annie","Hongyu","Tryndamere","Vladimir","Lissandra","Caitlyn","Leona","Taric","Soraka","Sona"}, new String []{"Item1", "Item2", "Item3", "Item4"}, 500, new String []{"50","0","1"}); //User Stats
	public Image background; //Background iamge
	
	private Container c;
	public static String key;
	public String url = Login.url;
	public static Server server;
	private JPanel panel;
	private Main main = new Main(this);
	private Top top = new Top(this);

	public Client(String name){
		background = new ImageIcon("TopBar.jpg").getImage();
		c = this.getContentPane();
		c.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Emblem of Legends");
		this.setResizable(false);
		panel = new JPanel(){ //Adding the background to the panel
			protected void paintComponent(Graphics g){
				Dimension d = getSize();
				g.drawImage(background, 0, 0, d.width, d.height, null);

				super.paintComponent(g);
			}
		};
		panel.setOpaque( false ); //Basic initializing stuff.
		c.add(panel,BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(10,10));
		panel.add(top, BorderLayout.NORTH); //Adding top panel
		panel.add(main, BorderLayout.CENTER); //Adding the main panel
		this.setVisible(true);
		this.setSize(1200,675);
	}
	private ServerServiceLocator sl = new ServerServiceLocator();
	public static Server s;
	public Client(String name, String k){
		
		key = k;
		try {
			s = sl.getBrianSoap(new URL(url));
			Team t = s.getTeam(name);
			server.beans.Profile p = s.getProfile(name);
			Client.user = new User(name, t.getChars(), new String []{"Item1", "Item2", "Item3", "Item4"}, 500, new String []{p.getWins()+"",p.getLosses()+"","~"});
			System.out.println(Client.user.getTeam3()[0]);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Team t = new Team();
		try {
			t = s.getTeam(name);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Client(name);
	}
	public static boolean isProperbutton() {
		return PROPERBUTTON;
	}
	public void setImgBackground(Image background){
		this.background = background;
		repaint();
	}
	public Top getTop(){
		return top;
	}
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	
}


