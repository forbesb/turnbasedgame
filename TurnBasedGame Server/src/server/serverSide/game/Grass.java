package server.serverSide.game;

import java.awt.Color;

import javax.swing.ImageIcon;

import server.serverSide.workers.*;

public class Grass extends Terrain{	
	
	public Grass(int x, int y, BattleThread t){
		super(x,y, t);
		this.setDef(10);
		setPassable(true);
		setBgcolor(new Color(0,255,0));
		setImg(new ImageIcon("plain.png"));
	}

	@Override
	public void terrEffect(Character c) {
		//heals 10 when stood on
		getThread().sendConsole(c.getName()+" has healed by 50!");
		getThread().sendDamage(c, -50);
		c.heal(50);
		return;
		
	}
}
