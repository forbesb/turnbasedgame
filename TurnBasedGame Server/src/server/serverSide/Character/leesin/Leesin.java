package server.serverSide.Character.leesin;

import server.serverSide.game.Character;
import server.serverSide.spells.Spell;

public class Leesin extends Character{;
	public Leesin( int x, int y, String t) {
		super(1500, 300, 300, 300, 1, 7, x, y, t, "Leesin");
		this.setSpell(new Spell[]{new LeeQSpell(this),new LeeWSpell(this),new LeeESpell(this),new LeeRSpell(this)});
	}
	public void tick(){
		super.tick();
		this.heal(getHp()*1/10);
	}
	
}
