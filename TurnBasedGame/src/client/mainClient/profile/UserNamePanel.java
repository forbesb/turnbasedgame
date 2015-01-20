package client.mainClient.profile;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import client.mainClient.backBone.Client;

@SuppressWarnings("serial")
public class UserNamePanel extends JPanel{
	private JLabel name;
	private String text;
	private GridBagConstraints gbc;
	private boolean theSwitch = true;
	public UserNamePanel(){
		this.gbc = new GridBagConstraints();
		this.text = Client.user.getName();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(240,0,0));
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.ipady = 140;
		gbc.weightx = 0.5;
		gbc.weighty = 0.8;
		this.name = new JLabel(text);
		name.setFont(new Font("Calibri",Font.BOLD,50));
		name.setForeground(new Color(255,255,255));
		this.add(name, gbc);
	}
	public UserNamePanel(String text){
		this.gbc = new GridBagConstraints();
		this.text = Client.user.getName();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(255,255,255));
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.weightx = 0.5;
		gbc.weighty = 0.8;
		this.name = new JLabel(text);
		name.setFont(new Font("Calibri",Font.BOLD,50));
		name.setForeground(new Color(32,32,240));
		this.add(name, gbc);
		theSwitch = false;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (theSwitch) g.setColor(new Color(0,0,0));
		else g.setColor(new Color(212,175,55));
		g.fillRect(0, 0, getWidth(), 20);
		g.fillRect(0, 0, 20, getHeight());
		g.fillRect(getWidth()-20,0, getHeight(),getWidth());
		g.fillRect(0,getHeight()-20, getWidth(), getHeight());
	}
	public void setText(String text){
		this.text = text;
	}
}