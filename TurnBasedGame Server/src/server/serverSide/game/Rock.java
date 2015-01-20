package server.serverSide.game;

import java.awt.Color;

import javax.swing.ImageIcon;

import server.serverSide.workers.*;
public class Rock extends Terrain {
	public Rock(int x, int y, BattleThread t){
		super(x, y, t);
		this.setDef(-1);
		setPassable(false);
		setBgcolor(new Color(20,20,20));
		setImg(new ImageIcon("rock.png"));

	}
	@Override
	public void terrEffect(Character c) {
		// no effect because it can't be stood on
		return;
	}

}
