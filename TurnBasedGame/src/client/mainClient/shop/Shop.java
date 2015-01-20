package client.mainClient.shop;

import java.awt.Color;

import client.mainClient.backBone.Center;
import client.mainClient.backBone.Tab;

@SuppressWarnings("serial")
public class Shop extends Tab{
	public Shop(Center panel){
		super();
		this.setBackground(new Color (160,32,240));
		this.add(new ShopPanel());
	}
	
}