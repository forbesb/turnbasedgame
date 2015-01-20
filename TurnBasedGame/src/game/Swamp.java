package game;

import gui.Game;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Swamp extends Terrain {

	public Swamp(int x, int y) {
		super(x, y);
		setDef(5);
		setPassable(true);
		setBgcolor(new Color(255,0,150));
		setImg(new ImageIcon("swamp.png"));
	}

	@Override
	public void terrEffect(Character c) {
		//poisons chars who stand on it
		c.addEffect(new Poison(4, c, 50));
		Game.console.println(c.getName()+" is Poisoned!");
	}

}
