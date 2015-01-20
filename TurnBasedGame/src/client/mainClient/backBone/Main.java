package client.mainClient.backBone;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import client.mainClient.backBone.Center;

@SuppressWarnings("serial")
public class Main extends JPanel{ //Main Panel
	private Tabs tabs = new Tabs(this);
	Client frame;
	private Center center = new Center(this);
	public Main(Client frame){
		this.frame = frame;
		this.setLayout(new BorderLayout());
		this.add(tabs, BorderLayout.NORTH); //The Tabs with the modified buttons
		this.add(center, BorderLayout.CENTER); //The Center panel
		this.setOpaque(false);
	}
	public Center getCenter() {
		return center;
	}
	public void setCenter(Center center) {
		this.center = center;
	}
	public Client getFrame(){
		return frame;
	}
	
	public Tabs getTabs() {
		  return tabs;
	}
	
}