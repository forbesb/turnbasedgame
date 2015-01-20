package game;

import javax.swing.ImageIcon;

public class Blank extends Terrain {

	public Blank(int x2, int y2) {
		super(x2, y2);
		setDef(5);
		setPassable(true);
		setImg(new ImageIcon("blank.png"));
		// TODO Auto-generated constructor stub
	}

	@Override
	public void terrEffect(Character c) {
		// TODO Auto-generated method stub

	}

}
