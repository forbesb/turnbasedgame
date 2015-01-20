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
public class VictoryDefeatPanel extends JPanel{
	private JLabel Victory = new JLabel("VICTORY");
	private JLabel Defeat = new JLabel("DEFEAT");
	private JLabel Ranking = new JLabel("RANKING");
	private JLabel[] info = {new JLabel(Client.user.getStats()[0]), new JLabel(Client.user.getStats()[1]), new JLabel(Client.user.getStats()[2])};
	private JLabel [] labels = {Victory, Defeat, Ranking};
	private Color [] colors = {new Color(32,240,32), new Color(240,10,10),new Color(255,255,255)};
	private GridBagConstraints gbc;
	public VictoryDefeatPanel(){
		gbc = new GridBagConstraints();
		this.setLayout(new GridBagLayout());
		this.setBackground(new Color(0,0,0));
		gbc.weightx = .5;
		gbc.gridy = 0;
		gbc.weighty = .5;
		gbc.anchor = GridBagConstraints.NORTH;
		for (int i = 0; i < 3; i ++){
			gbc.gridy = 0;
			gbc.gridx = i*5;
			labels[i].setFont(new Font("Calibri",Font.BOLD, 30));
			labels[i].setForeground(colors[i]);
			info[i].setFont(new Font("Calibri",Font.BOLD, 30));
			info[i].setForeground(colors[i]);
			this.add(labels[i], gbc);
			gbc.gridy = 1;
			this.add(info[i],gbc);
		}
	}
	public void paintComponent (Graphics g){
		super.paintComponent(g);
		g.setColor(new Color(255,255,255));
		for (int i = 1; i < 4; i ++){
			g.drawLine(i*(this.getWidth()/3), 0, i*(this.getWidth()/3), this.getHeight());
		}
		int h = 35;
		g.drawLine(0, h, getWidth(), h);
	}
}