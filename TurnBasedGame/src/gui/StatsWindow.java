package gui;

import game.Character;
import game.EffectTimer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatsWindow extends JPanel {
	private game.Character selchar;
	private JLabel image, hpbar, attack, defence, magicres, range, speed;
	private SpellButton[] spells = new SpellButton[4];
	
	public void print(){ 
		//for testing
		 System.out.println(image.getWidth());
		 System.out.println(image.getHeight());
		 System.out.println(image.getSize());
	}
	
	public StatsWindow() {
		super();
		this.setLayout(new BorderLayout());
		Container cont = new Container();
		cont.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0; //0,0
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 5; //gridbag constraint setting
		c.weightx = 1;
		c.weighty = 1;
		
		hpbar = new JLabel();
		hpbar.setBackground(new Color(255,0,0));
		hpbar.setText("hp bar");
		cont.add(hpbar, c);
		
		c.gridx = 0; //0,1
		c.gridy = 1;
		c.gridheight = 5;
		c.gridwidth = 4;
		c.weightx = 1.5;
		c.weighty = 1.5;
		image = new JLabel();
		try {
			image.setIcon(new ImageIcon(ImageIO.read(new File("placeholder.png")).getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		image.setHorizontalTextPosition(JLabel.CENTER);
		image.setVerticalTextPosition(JLabel.BOTTOM);
		FontMetrics fm = image.getFontMetrics(image.getFont());
		int w = fm.stringWidth("0000000000000000");
	    int h = 10;
	    Dimension size = new Dimension(w, h);
	    image.setMinimumSize(size);
		c.anchor = GridBagConstraints.NORTH;
		c.insets = new Insets(5,5,5,5);
		cont.add(image, c);
		c.insets = new Insets(0,0,0,0);
		c.gridx = 4;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1.1;
		attack = new JLabel();
		//attack.setMaximumSize(size);
		attack.setText("Attack: 0");
		cont.add(attack, c);
		
		c.gridx = 4;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1.1;
		defence = new JLabel();
		defence.setText("Defence: 0");
		cont.add(defence, c);
		
		c.gridx = 4;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1.1;
		magicres = new JLabel();
		magicres.setText("Magic Resist: ");
		
		cont.add(magicres, c);
		
		c.gridx = 4;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1.1;
		range = new JLabel();
		range.setText("Range: 0");
		cont.add(range, c);
		
		c.gridx = 4;
		c.gridy = 5;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		c.weighty = 1.1;
		speed = new JLabel();
		speed.setText("Speed: 0");
		cont.add(speed, c);
		
		
		Container spellc = new Container();
		spellc.setLayout(new GridLayout(4,1));
		for (int i = 0; i<4; i++){ //add spell buttons
			spells[i] = new SpellButton();
			spells[i].setFont(new Font("Serif", Font.PLAIN, 12));
			spells[i].setText("Spell "+i);
			spellc.add(spells[i]);
		}
		c.gridx = 0;
		c.gridy = 6;
		c.gridheight = 1;
		c.gridwidth = 5;
		c.weightx = 20;
		c.weighty = 1;
		cont.add(spellc, c); //add spell button container to main container
		
		this.add(cont, BorderLayout.CENTER);
	}
	
	public void setSelected(game.Character c){
		selchar = c;
		image.setIcon(new ImageIcon(c.getIcon().getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH))); //bad things
		image.setHorizontalTextPosition(JLabel.CENTER);//change image to match selected image
		image.setVerticalTextPosition(JLabel.TOP);
		image.setText(selchar.getName());
		String mr = c.getMagicres()+"";
		if (c.getMagicres() > 10000) mr = "High";
		String de = c.getDefence() + "";
		if (c.getDefence() > 10000) de = "High";
		
		attack.setText("Attack: "+c.getAttack());
		defence.setText("Defence: "+ de); //change all texts to match proper texts
		magicres.setText("MagicRes: "+mr);
		range.setText("Range: "+ c.getRange());
		speed.setText("Speed: "+ c.getSpeed());			
		
		for (int i = 0; i<4; i++){
			spells[i].setSpell(selchar.getSpell(i));
		}
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (selchar!=null){//paint hp bar in correct spot
			hpbar.setText(selchar.getCurrentHP()+"/"+selchar.getHp());
			int x = 0;
			int y = hpbar.getY()+hpbar.getHeight()/2-20;
			int length = 279; //280 -1 so the border can be seen
			selchar.drawHealthBar(g, x, y, length, 40);
			
			int end = 6;
			ArrayList<EffectTimer> effs = selchar.getEffs();
			if (effs.size()<end){
				end = effs.size();
			}
			g.setColor(new Color(0,0,0));
			g.setFont(new Font("Serif", Font.BOLD, 12));
			g.drawString("Effects", 60, 140);
			g.setFont(new Font("Serif", Font.PLAIN, 12));
			for (int i = 0; i < end; i ++){
				g.drawString(effs.get(i).toString(), 35, 158+i*18);
			}
		}
	}
	
	public SpellButton getButton(int index){
		return spells[index];
	}
	
	public String getSelTeam(){
		if (selchar == null){
			return "";
		}
		return selchar.getTeam();
	}

}
