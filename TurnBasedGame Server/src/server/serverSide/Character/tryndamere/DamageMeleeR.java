package server.serverSide.Character.tryndamere;

import server.serverSide.game.Character;
import server.serverSide.spells.Spell;

public class DamageMeleeR extends Spell {

	public DamageMeleeR(Character c) {
		super(false, true, false, c);
		setName("Execute");
		setRange(2);
		setMaxCD(10);
		setOnCD();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activeEffect(Character target) {
		// TODO Balance
		int damage = 1000;
		double modifier = 1 - (((double)target.getCurrentHP()/target.getHp()));
		int additionalDamage = (int) (modifier*50);
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		return target.defendSpell(damage+additionalDamage);
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
