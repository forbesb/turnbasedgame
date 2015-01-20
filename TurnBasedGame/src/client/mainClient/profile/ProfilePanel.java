package client.mainClient.profile;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import client.mainClient.profile.ImagePanel;

@SuppressWarnings("serial")
public class ProfilePanel extends JPanel{ //This is the profile panel
	private GridBagConstraints gbc = new GridBagConstraints();
	private Profile profile;
	private static InQueueStatus iqs;
	public ProfilePanel(Profile panel){
		this.setIqs(new InQueueStatus(this));
		this.profile = panel;
		this.setBackground(new Color(255,255,255));
		this.setLayout(new GridBagLayout());
		gbc.insets = new Insets(5,5,5,5);
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 10;
		this.add(new ImagePanel(this),gbc);
		gbc.gridx = 10;
		gbc.weightx = 0.5;
		gbc.weighty = 1.0;
		gbc.gridwidth = 30;
		gbc.gridy = 0;
		this.add(new UserNamePanel(), gbc);
		gbc.ipady = 208;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(new VictoryDefeatPanel(),gbc);
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridy = 1;
		gbc.gridx = 10;
		this.add(iqs,gbc);
		
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public static InQueueStatus getIqs() {
		return iqs;
	}
	public void setIqs(InQueueStatus iqs) {
		this.iqs = iqs;
	}
}