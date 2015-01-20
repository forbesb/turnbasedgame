package server.serverSide.spells.damageMeleeOne;

import server.serverSide.game.Character;
import server.serverSide.spells.Spell;

public class DamageMeleeR extends Spell {

	public DamageMeleeR(Character c) {
		super(false, true, false, c);
		setName("Execute");
		setRange(4);
		setMaxCD(5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activeEffect(Character target) {
		// TODO Balance
		int damage = 25;
		double modifier = 1 - (((double)target.getCurrentHP()/target.getHp()));
		int additionalDamage = (int) (modifier*25);
		return target.defendSpell(damage+additionalDamage);
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
