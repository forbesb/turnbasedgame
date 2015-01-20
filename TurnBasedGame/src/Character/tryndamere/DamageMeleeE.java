package Character.tryndamere;

import spells.Spell;
import game.Character;

public class DamageMeleeE extends Spell {

	public DamageMeleeE(Character c) {
		super(false, true, true, c);
		setName("Spell Shield");
		setRange(0);
		setMaxCD(3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activeEffect(Character target) {
		// spell shield
		target.addSpellShield();
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return true;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
