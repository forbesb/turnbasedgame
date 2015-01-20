package client.mainClient.shop;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ItemShelf extends JPanel implements MouseListener{
	private String [] message;
	private int value;
	public ItemShelf(){
		addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		ItemPanel.itemEntered = true;
		ItemPanel.xcoor = this.getX()+90;
		ItemPanel.ycoor = this.getY()+10;
		ItemPanel.data = new String[][]{message,{"Price: " + value}};
	}

	@Override
	public void mouseExited(MouseEvent e) {	
		ItemPanel.itemEntered = false;
	}
	public void setValue(int value){
		this.value = value;
	}
	public void setMessage(String [] str){
		this.message = str;
	}
	
	
}