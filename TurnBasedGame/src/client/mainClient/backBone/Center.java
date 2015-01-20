package client.mainClient.backBone;

import java.awt.CardLayout;

import javax.swing.JPanel;


import client.mainClient.armory.Armory;
import client.mainClient.empty.Home;
import client.mainClient.instructions.Instructions;
import client.mainClient.profile.Profile;
import client.mainClient.shop.Shop;
import client.mainClient.teamselection.TeamSelection;

@SuppressWarnings("serial")
public class Center extends JPanel{ //Card layout system
	private Main main;
	private String showPanel = "Home";
	private Profile profile;
	private JPanel [] panels = {new Home(this), new Instructions(this), setProfile(new Profile(this)), new Shop(this), new TeamSelection(this), new Armory(this)};
	private String [] names = {"Home", "Instructions", "Profile", "Shop", "TeamSelection", "Armory"};;
	private CardLayout cl;
	public Center(Main main){
		this.main = main;
		cl = new CardLayout();
		this.setOpaque(false);
		this.setLayout(cl);
		
		for (int i = 0; i < 6; i ++){
			this.add(panels[i],names[i]);
		}
		
	}
	public void setShowPanel(String name){
		this.showPanel = name;
		cl.show(this, showPanel);
	}
	public Main getMain() {
		return main;
	}
	public void setMain(Main main) {
		this.main = main;
	}
	public Profile getProfile() {
		return profile;
	}
	public Profile setProfile(Profile profile) {
		this.profile = profile;
		return profile;
	}
	
}