package server.serverSide.Character.annie;
import javax.swing.ImageIcon;

import server.serverSide.spells.Spell;

import server.serverSide.game.Character;
public class Annie extends Character{

	public Annie(int x, int y, String t) {
		super(1500, 500, 50, 50, 7, 4, x, y, t, "Annie");
		setSpell(new Spell[]{new AnnieQSpell(this),new AnnieWSpell(this),new AnnieESpell(this),new AnnieRSpell(this)});
		
	}

}
