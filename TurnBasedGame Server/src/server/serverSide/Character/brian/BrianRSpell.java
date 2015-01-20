package server.serverSide.Character.brian;

import server.serverSide.game.Character;
import server.serverSide.game.DeathMark;
import server.serverSide.spells.Spell;

public class BrianRSpell extends Spell{

	public BrianRSpell(Character c) {
		super(false, true, false, c);
		this.setRange(7);
		this.setName("Death Mark");
		this.setMaxCD(8);
		setOnCD();
	}

	@Override
	public boolean activeEffect(Character target) {
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		target.addEffect(new DeathMark(target));
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
