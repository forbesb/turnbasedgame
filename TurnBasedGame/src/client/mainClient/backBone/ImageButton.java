package client.mainClient.backBone;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import client.login.GameGenerator;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ImageButton extends MyButton implements MouseListener{
	private Image icon = new ImageIcon("playButton.png").getImage();
	private Top top;
	public ImageButton(int x, Top top) {
		super(x);
		this.top = top;
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(icon, 0, 0, getWidth(), getHeight(), null);
	}
	public Top getTop() {
		return top;
	}
	public void setTop(Top top) {
		this.top = top;
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if (getNames()[getController()].equals("Play") && !top.getClient().getMain().getCenter().getProfile().getProfPanel().getIqs().isInQueue()){
			//ServerImplementation serv = new ServerImplementation();

			if (Client.isProperbutton()){
				GameGenerator g = new GameGenerator(Client.s, Client.key, Client.user.getName());
			}
			top.getClient().getMain().getCenter().getProfile().getProfPanel().getIqs().setInQueue(true);
			top.getClient().getMain().getCenter().setShowPanel("Profile");
		}
	}
}