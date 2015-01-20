package server.serverSide.game;

import java.awt.Color;

import javax.swing.ImageIcon;

import server.serverSide.workers.*;

public class Swamp extends Terrain {

	public Swamp(int x, int y, BattleThread t) {
		super(x, y, t);
		setDef(5);
		setPassable(true);
		setBgcolor(new Color(255,0,150));
		setImg(new ImageIcon("swamp.png"));
	}

	@Override
	public void terrEffect(Character c) {
		//poisons chars who stand on it
		c.addEffect(new Poison(4, c, 50));
		getThread().sendConsole(c.getName()+" is Poisoned!");
	}

}
