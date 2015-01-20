package Character.tryndamere;

import game.Character;
import spells.Spell;

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
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return target.defendSpell(damage+additionalDamage);
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
