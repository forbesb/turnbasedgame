package client.mainClient.armory;

import javax.swing.*;

import client.mainClient.armory.ArmoryPanel;
import client.mainClient.armory.CharacterPanel;
import client.mainClient.armory.DefaultCenter;

import java.awt.*;

import game.Character;

@SuppressWarnings("serial")
public class Right extends JPanel {
	DefaultCenter defaultCenter = new DefaultCenter();
	private CardLayout cl;
	private CharacterPanel [] panels = new CharacterPanel [12];
	private String currentName = "default";
	public Right(ArmoryPanel ar) {
		this.setLayout(cl = new CardLayout());
		this.add(defaultCenter, "default");
		int j = 0;
		for (Character i : ar.getCharacters()){
			panels[j] = new CharacterPanel(i, j);
			this.add(panels[j],i.getName());
			j++;
		}
		cl.show(this, currentName);
	}
	public void showPanel(String name){
		if (name.equals(currentName)){
			cl.show(this, "default");
			this.currentName = "default";
		}
		else{
			cl.show(this, name);
			this.currentName = name;
		}
	}
	
}
