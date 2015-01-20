package client.mainClient.armory;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@SuppressWarnings("serial")
public class MyImageIcon extends JPanel implements MouseListener{
	private Left sPanel;
	private String name;
	private Image icon;
	public MyImageIcon(Left s, ImageIcon icon, String name){
		this.sPanel = s;
		this.name = name;
		this.icon = icon.getImage();
		this.setLayout(new BorderLayout());
		addMouseListener(this);
	
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(icon,0,0,getWidth(),getHeight(),null);
	}
	public String toString() {
		return name;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		sPanel.getPanel().getRight().showPanel(name);
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
