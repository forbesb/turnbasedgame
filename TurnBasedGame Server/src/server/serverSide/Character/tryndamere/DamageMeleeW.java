package server.serverSide.Character.tryndamere;

import server.serverSide.spells.Spell;
import server.serverSide.game.Character;

public class DamageMeleeW extends Spell {

	public DamageMeleeW(Character c) {
		super(false, true, true, c);
		setName("Phys Shield");
		setRange(0);
		setMaxCD(4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activeEffect(Character target) {
		//physical shield
		target.addPhysShield();
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		return true;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
