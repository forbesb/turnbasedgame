package server.serverSide.spells.damageMeleeOne;

import server.serverSide.game.Character;
import server.serverSide.spells.Spell;

public class DamageMeleeW extends Spell {

	public DamageMeleeW(Character c) {
		super(false, true, true, c);
		setName("Phys Shield");
		setRange(0);
		setMaxCD(3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activeEffect(Character target) {
		//physical shield
		target.addPhysShield();
		return true;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
