package client.mainClient.armory;

import java.awt.*;

import javax.swing.*;

import client.mainClient.armory.ArmoryPanel;

@SuppressWarnings("serial")
public class Left extends JPanel{
	private ArmoryPanel superP;
	public Left(ArmoryPanel s){
		this.superP = s;
		this.setLayout(new GridLayout(4,3,10,10));
		this.setBackground(new Color(255,255,255));
		for (int i = 0; i < 12; i ++){
			MyImageIcon image = new MyImageIcon(this,new ImageIcon(superP.getCharacters()[i].getIcon().getImage()),superP.getNames()[i]);
			this.add(image);
		}
	}
	public ArmoryPanel getPanel(){
		return superP;
	}
}