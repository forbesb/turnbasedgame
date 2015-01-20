package server.serverSide.Character.taric;

import server.serverSide.spells.Spell;
import server.serverSide.game.Character;


public class Taric extends Character{

	public Taric(int x, int y, String t) {
		super(6000, 150, 300, 300, 1, 4, x, y, t, "Taric");
		super.setSpell(new Spell[]{new TaricQSpell(this),new TaricWSpell(this),new TaricESpell(this),new TaricRSpell(this)});
	}
	
}
