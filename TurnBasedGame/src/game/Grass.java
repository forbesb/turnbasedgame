package game;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Grass extends Terrain{	
	
	public Grass(int x, int y){
		super(x,y);
		this.setDef(10);
		setPassable(true);
		setBgcolor(new Color(0,255,0));
		setImg(new ImageIcon("plain.png"));
	}

	@Override
	public void terrEffect(Character c) {
		//heals 10 when stood on
		gui.Game.console.println(c.getName()+" has healed by 50!");
		c.heal(50);
		return;
		
	}
}
