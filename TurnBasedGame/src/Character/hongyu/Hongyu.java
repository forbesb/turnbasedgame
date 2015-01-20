package Character.hongyu;
import javax.swing.ImageIcon;

import game.Character;
import gui.ConsoleBox;
import spells.Spell;
public class Hongyu extends Character{
	public Hongyu(int x, int y, String t,  ConsoleBox c) {
		super(3000, 50, 300, 300, 4, 5, x, y, t, "Hongyu", c, new ImageIcon("hongwong.png"), new ImageIcon("HongyuIcon.png"));
		super.setSpell(new Spell[]{new HongyuQSpell(this), new HongyuWSpell(this), new HongyuESpell(this), new HongyuRSpell(this)});
		
	}
}
