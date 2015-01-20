package client.mainClient.backBone;

import java.awt.BorderLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Tab extends JPanel{ //A super class for each Tab
	private JPanel [] panels = {new JPanel(), new JPanel(), new JPanel(), new JPanel()};
	private String [] positions = {BorderLayout.NORTH, BorderLayout.EAST, BorderLayout.WEST, BorderLayout.SOUTH};
	public Tab(){
		this.setLayout(new BorderLayout());
		for (int i = 0; i < 4; i ++){
			panels[i].setOpaque(false);
			this.add(panels[i],positions[i]);
		}
	}

	
}