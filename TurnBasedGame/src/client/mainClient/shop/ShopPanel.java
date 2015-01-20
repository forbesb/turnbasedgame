package client.mainClient.shop;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import client.mainClient.shop.ConfirmPanel;

@SuppressWarnings("serial")
public class ShopPanel extends JPanel{
	private GridBagConstraints gbc = new GridBagConstraints();
	public ShopPanel(){
		
		this.setLayout(new GridBagLayout());
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridwidth = 3;
		this.add(new ItemPanel(),gbc);
		gbc.gridx = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0.1;
		this.add(new ConfirmPanel(), gbc);
	}
}