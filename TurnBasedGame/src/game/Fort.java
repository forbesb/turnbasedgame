package game;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Fort extends Terrain {

	public Fort(int x, int y) {
		super(x,y);
		setDef(50); 
		setPassable(true);
		setBgcolor(new Color(205,184,158));
		setImg(new ImageIcon("fort.png"));
	}

	@Override
	public void terrEffect(Character c) {
		//increases range when stood on
		c.addEffect(new EffectTimer(1, c, EffectTimer.RANGE, 1));
		gui.Game.console.println(c.getName()+" has an increased range!");
	}

}
