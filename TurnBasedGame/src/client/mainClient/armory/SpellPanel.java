package client.mainClient.armory;
import javax.swing.*;

import client.mainClient.armory.CharacterPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.Character;
@SuppressWarnings("serial")
public class SpellPanel extends JPanel implements MouseListener{ //This is a spell panel. This controls which spell is shown in the almanac
	private static String [] spellHeadings = {"Passive","Spell 1", "Spell 2", "Spell 3", "Spell 4"};
	private String [] description;
	private JLabel message;
	private GridBagConstraints gbc;
	private CharacterPanel panel;
	public SpellPanel(CharacterPanel panel, int i, String [] description, Character c){
		this.setLayout(new GridBagLayout());
		this.addMouseListener(this);
		this.setBackground(new Color(255,255,255));
		this.panel = panel;
		gbc = new GridBagConstraints();
		message = new JLabel(spellHeadings[i]);
		message.setFont(new Font("Calibri",Font.BOLD,20));
		message.setForeground(new Color(0,0,0));
		message.setOpaque(false);
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.CENTER;
		this.add(message,gbc);
		if (i != 0){
			String [] firstPart;
			int range = c.getSpell(i-1).getRange();
			if (range != -1 && range!= 0){
				firstPart = new String []{"Name: " + c.getSpell(i-1).getName(),"Cooldown: " + c.getSpell(i-1).getMaxCD() + " turns", "Range: "+c.getSpell(i-1).getRange()};
			}else if (range == 0){
				firstPart = new String []{"Name: " + c.getSpell(i-1).getName(),"Cooldown: " + c.getSpell(i-1).getMaxCD() + " turns", "Range: SELF"};
			}
			else{
				firstPart = new String []{"Name: " + c.getSpell(i-1).getName(),"Cooldown: " + c.getSpell(i-1).getMaxCD() + " turns", "Range: GLOBAL"};
			}
			
			String [] total = new String[firstPart.length+description.length];
			for (int j = 0; j < 3; j ++){
				total[j] = firstPart[j];
			}for (int j = 0; j < description.length; j++){
				total[j+3] = description[j];
			}
			this.description = total;
		}else
			this.description = description;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(new Color(0,0,0));
		g.drawRect(0, 0, getWidth(), getHeight());
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		panel.setInIcon(true);
		panel.setData(description);
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		panel.setInIcon(false);
		
	}
	
}
