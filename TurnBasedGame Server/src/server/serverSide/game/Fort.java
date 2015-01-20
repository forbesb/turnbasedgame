package server.serverSide.game;

import java.awt.Color;

import server.serverSide.workers.*;

import javax.swing.ImageIcon;

public class Fort extends Terrain {

	public Fort(int x, int y, BattleThread t) {
		super(x,y, t);
		setDef(50); 
		setPassable(true);
		setBgcolor(new Color(205,184,158));
		setImg(new ImageIcon("fort.png"));
	}

	@Override
	public void terrEffect(Character c) {
		//increases range when stood on
		c.addEffect(new EffectTimer(1, c, EffectTimer.RANGE, 1));
		getThread().sendEffect(c, EffectTimer.RANGE, 1, 1); //apparently this is backwards? dunno why, must've screwed up somewhere
		getThread().sendConsole(c.getName()+" has an increased range!");
	}

}
