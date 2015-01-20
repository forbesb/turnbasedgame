package client.mainClient.backBone;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import client.mainClient.backBone.ImageButton;
import client.mainClient.backBone.MyButton;

@SuppressWarnings("serial")
public class Top extends JPanel{
	private ImageButton play;
	private MyButton home;
	private JLabel [] labels;
	GridBagConstraints gbc;
	private Client client;
	public Top(Client client){
		this.client = client;
		this.setOpaque(false);
		labels = new JLabel[]{new JLabel ("Developed by Brian Forbes"),new JLabel(" and Hongyu Wang")};
		play = new ImageButton(6, this);
		home = new MyButton(5, client.getMain().getTabs());
		gbc = new GridBagConstraints();
		
		for (int i = 0; i < 2; i++){
			labels[i].setFont(new Font("comic sans",5,20));
			labels[i].setForeground(new Color(255,255,255));
		}
		
		setLayout(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 4;
		gbc.weightx = 1.0;
		gbc.insets = new Insets(5,5,5,5);
		gbc.ipadx = 100;
		gbc.fill = GridBagConstraints.CENTER; 
		this.add(home,gbc);
		gbc.ipadx = 0;
		gbc.gridheight = 6;
		gbc.gridx = 2;
		gbc.fill = GridBagConstraints.BOTH;
		this.add(play,gbc);
		gbc.gridx = 3;
		gbc.gridheight = 1;
		gbc.fill = 0;
		this.add(labels[0],gbc);
		gbc.gridy = 1;
		gbc.ipady = 0;
		this.add(labels[1],gbc);
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
}