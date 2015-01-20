package client.mainClient.empty;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import client.mainClient.backBone.Center;

@SuppressWarnings("serial")
public class Home extends JPanel{ //The home Panel
	private GridBagConstraints gbc;
	public Home(Center panel){
		this.setOpaque(false);
		this.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(new CentreLabel(), gbc);
	}
}