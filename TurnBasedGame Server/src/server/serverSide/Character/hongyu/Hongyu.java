package server.serverSide.Character.hongyu;

import server.serverSide.game.Character;
import server.serverSide.spells.Spell;

public class Hongyu extends Character{
	public Hongyu(int x, int y, String t) {
		super(3000, 50, 300, 300, 4, 5, x, y, t, "Hongyu");
		super.setSpell(new Spell[]{new HongyuQSpell(this), new HongyuWSpell(this), new HongyuESpell(this), new HongyuRSpell(this)});
		
	}
}
