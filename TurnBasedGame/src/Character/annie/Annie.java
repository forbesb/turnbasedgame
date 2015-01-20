package Character.annie;
import javax.swing.ImageIcon;

import spells.Spell;

import game.Character;
import gui.ConsoleBox;
public class Annie extends Character{

	public Annie(int x, int y, String t,  ConsoleBox c) {
		super(1500, 500, 50, 50, 7, 4, x, y, t, "Annie", c, new ImageIcon("annie.png"), new ImageIcon("AnnieIcon.png"));
		setSpell(new Spell[]{new AnnieQSpell(this),new AnnieWSpell(this),new AnnieESpell(this),new AnnieRSpell(this)});
		
	}

}
