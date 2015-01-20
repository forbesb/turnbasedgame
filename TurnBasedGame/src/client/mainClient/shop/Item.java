package client.mainClient.shop;

import java.util.Hashtable;

import javax.swing.ImageIcon;

public class Item{
	public boolean owned;
	public boolean selected;
	public int x, y;
	private ImageIcon itemImage;
	private int value;
	private String [] description;
	private Hashtable<String, Integer> modifierTable = new Hashtable<String, Integer>();
	public Item(int value, String [] description, Hashtable<String, Integer> modifierTable, ImageIcon image){
		this.itemImage = image;
		this.value = value;
		this.description = description;
		this.modifierTable = modifierTable;
	}

	public int getValue(){
		return value;
	}
	public String [] getDescription(){
		return description;
	}
	public Hashtable<String, Integer> getStats(){
		return modifierTable;
	}
	public void setTable(Hashtable<String, Integer> table){
		this.modifierTable = table;
	}
	public String toString(){
		return description[0];
	}
	public ImageIcon getImage(){
		return itemImage;
	}
	public void setImage(ImageIcon image){
		this.itemImage = image;
	}
}