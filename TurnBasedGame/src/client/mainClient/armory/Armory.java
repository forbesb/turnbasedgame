package client.mainClient.armory;

import java.awt.Color;

import client.mainClient.backBone.Center;
import client.mainClient.backBone.Tab;

@SuppressWarnings("serial")

public class Armory extends Tab{
	public Armory(Center panel){
		super();
		this.setBackground(new Color (32,178,179));
		this.add(new ArmoryPanel());
	}
	
}