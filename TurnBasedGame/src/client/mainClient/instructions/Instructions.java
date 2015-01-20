package client.mainClient.instructions;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import client.mainClient.backBone.Center;
import client.mainClient.backBone.Tab;



@SuppressWarnings("serial")
public class Instructions extends Tab{
	private GridBagConstraints gbc;
	public Instructions(Center panel){
		super();
		this.setBackground(new Color (0,0,0));
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(new InstructionsPanel(), gbc);
		
	}
}