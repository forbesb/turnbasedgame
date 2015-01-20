package client.mainClient.teamselection;

import java.awt.Color;

import client.mainClient.backBone.Center;
import client.mainClient.backBone.Tab;

@SuppressWarnings("serial")
public class TeamSelection extends Tab{
	public TeamSelection(Center panel){
		super();
		this.setBackground(new Color (0,0,255));
		this.add(new TeamSelectionPanel());
	}
	
}