package client.mainClient.profile;

import java.awt.Color;


import client.mainClient.backBone.Center;
import client.mainClient.backBone.Tab;

@SuppressWarnings("serial")
public class Profile extends Tab{
	private Center center;
	private ProfilePanel profPanel;
	public Profile(Center panel){
		super();
		center = panel;
		this.setBackground(new Color (255,0,0));
		this.add(setProfPanel(new ProfilePanel(this)));
	}
	public Center getCenter() {
		return center;
	}
	public void setCenter(Center center) {
		this.center = center;
	}
	public ProfilePanel getProfPanel() {
		return profPanel;
	}
	public ProfilePanel setProfPanel(ProfilePanel profPanel) {
		this.profPanel = profPanel;
		return profPanel;
	}
	
}