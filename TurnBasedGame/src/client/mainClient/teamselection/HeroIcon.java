package client.mainClient.teamselection;

import javax.swing.ImageIcon;

public class HeroIcon{
	private String name;
	private String [] description;
	private ImageIcon image;
	public HeroIcon(String name, String imgName, String [] description){
		this.name = name;
		this.setDescription(description);
		this.image = new ImageIcon(imgName);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String [] getDescription() {
		return description;
	}
	public void setDescription(String [] description) {
		this.description = description;
	}
	public ImageIcon getImage(){
		return image;
	}
}