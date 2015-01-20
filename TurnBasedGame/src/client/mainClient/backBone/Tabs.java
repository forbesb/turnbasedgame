package client.mainClient.backBone;

import java.awt.GridLayout;

import javax.swing.JPanel;

import client.mainClient.backBone.MyButton;

@SuppressWarnings("serial")
public class Tabs extends JPanel{
	private Main panel;
	public Tabs(Main panel){
		this.setPanel(panel);
		this.setOpaque(false);
		this.setSize(1200,200);
		this.setLayout(new GridLayout(1,5,5,0));
		for (int i = 0; i < 5; i ++){
			this.add(new MyButton(i, this));
		}
		
	}
	public Main getPanel() {
		return panel;
	}
	public void setPanel(Main panel) {
		this.panel = panel;
	}
}