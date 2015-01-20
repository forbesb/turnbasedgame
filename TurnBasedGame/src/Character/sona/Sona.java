package Character.sona;
import javax.swing.ImageIcon;

import game.Character;
import gui.ConsoleBox;
import spells.Spell;
public class Sona extends Character{

	public Sona(int x, int y, String t, ConsoleBox c) {
		super(1200, 300, 100, 100, 7, 5, x, y, t, "Sona", c, new ImageIcon("Sona.png"),new ImageIcon("SonaIcon.png"));
		this.setSpell(new Spell[]{new SonaQSpell(this),new SonaWSpell(this),new SonaESpell(this),new SonaRSpell(this)});
	}

}
