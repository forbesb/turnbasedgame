package game;

import java.awt.Color;

import javax.swing.ImageIcon;

public class Rock extends Terrain {
	public Rock(int x, int y){
		super(x, y);
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
