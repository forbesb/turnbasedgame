package Character.taric;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import spells.Spell;
import game.Character;
import game.EffectTimer;
import gui.ConsoleBox;
public class Taric extends Character{

	public Taric(int x, int y, String t,ConsoleBox c) {
		super(6000, 150, 300, 300, 1, 4, x, y, t, "Taric", c, new ImageIcon("literallyTaric.png"), new ImageIcon("TaricIcon.png"));
		super.setSpell(new Spell[]{new TaricQSpell(this),new TaricWSpell(this),new TaricESpell(this),new TaricRSpell(this)});
	}
	
	public void tick(){
		super.tick();
		int a = 0;
		String team [] = new String []{"A","B"};
		if (getTeam().equals(team[a])){
			a=1;
		}	
	}
}
