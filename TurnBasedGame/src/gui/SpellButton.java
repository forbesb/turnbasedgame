package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import spells.Spell;

public class SpellButton extends JButton {
	private Spell spell;
	public SpellButton() {
		//generic constructor
		super();
		this.setBackground(new Color(255,0,0)); //red color looks nice
		this.setFocusPainted(false); //makes lines not show up when it gets focus
		//this.setBorder(BorderFactory.createEmptyBorder());
		//this.setContentAreaFilled(false);
			
		//used these before, liked look with borders/background better ^
		// TODO Auto-generated constructor stub
	}
	
	public SpellButton(Spell spell){
		//unused
		this.spell = spell;
		this.setBorder(BorderFactory.createEmptyBorder());
		this.setContentAreaFilled(false);
		this.setText(spell.getName());
	}
	
	public Spell getSpell(){
		return spell;
	}
	
	public void setSpell(Spell spell){
		this.spell = spell;
		//change spell and text to match
		
		this.setText(spell.getName());
	}
	
	
	public void paintComponent(Graphics g){
		g.setColor(getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		if (spell!=null){
			//mostly the same code as the character's draw health bar
			double percent = 1 - (double)spell.getCooldown()/spell.getMaxCD();
			//inverted percent because the more cooldown, the smaller the bar should be.
			g.setColor(new Color(0,0,0));
			g.drawRect(0, 0, this.getWidth(), this.getHeight()); //draw black border
			if (percent>0.6)
				g.setColor(new Color(0,255,0));
			else if (percent>0.3)   //changes color based on percentage
				g.setColor(new Color(255,200,0));
			else
				g.setColor(new Color(255,0,0));
			g.fillRect(1, 1, (int)(this.getWidth()*percent)-1, this.getHeight()-1);//this makes it in the black border
			g.setColor(new Color(0,0,0));
			g.drawString(String.valueOf(spell.getCooldown()), this.getWidth()-15, this.getHeight()/2);
			g.drawString(spell.getName(), 10, 3*this.getHeight()/4);
		}
	}
	
	
	
}
