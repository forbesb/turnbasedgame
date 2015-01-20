package client.mainClient.teamselection;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;


import client.mainClient.backBone.Client;

@SuppressWarnings("serial")
public class HeroShelf extends JPanel implements MouseListener{
	private String [] data;
	private String name;
	private TeamSelectionPanel panel;
	private JLabel hero;
	public HeroShelf(TeamSelectionPanel t, JLabel h){
		addMouseListener(this);
		this.panel = t;
		this.hero = h;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < Client.user.getTeam().size(); i ++){
			if (Client.user.getTeam().get(i).equals(name)){
				Client.user.removeFromTeam(name);
				panel.getTeam().setImg(hero.getIcon());
				panel.getTeam().reset();
				panel.repaint();
				return;
			}
		}
		if (Client.user.getTeam().size()<10){
			Client.user.addToTeam(name);
			panel.getTeam().setImg(hero.getIcon());
			panel.getTeam().setStr(name);
			panel.getTeam().reset();;
		}
		panel.repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		panel.setHeroEntered(true);
		panel.setXcoor(this.getX()+90);
		panel.setYcoor(this.getY()+10);
		panel.setData(this.data);
		panel.setName(this.getName());
		panel.repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {	
		panel.setHeroEntered(false);
		panel.repaint();
	}
	public void setMessage(String [] str){
		this.data = str;
	}
	
	
	
}