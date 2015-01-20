package Character.leesin;

import javax.swing.ImageIcon;

import game.Character;
import gui.ConsoleBox;
import spells.Spell;

public class Leesin extends Character{;
	public Leesin( int x, int y, String t,  ConsoleBox c) {
		super(1500, 300, 300, 300, 1, 7, x, y, t, "Leesin", c, new ImageIcon("leeSinga.png"), new ImageIcon("LeesinIcon.png"));
		this.setSpell(new Spell[]{new LeeQSpell(this),new LeeWSpell(this),new LeeESpell(this),new LeeRSpell(this)});
	}
	public void tick(){
		super.tick();
		this.heal(getHp()*1/10);
	}
	
}
