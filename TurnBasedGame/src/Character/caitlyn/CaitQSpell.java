package Character.caitlyn;

import game.Character;
import spells.Spell;

public class CaitQSpell extends Spell{

	public CaitQSpell(Character caster) {
		super(false, true, false, caster);
		setName("LR Shot");
		setMaxCD(2);
		setRange(-1);
	}

	@Override
	public boolean activeEffect(Character target) {
		int damage = getCaster().getAttack()/2;
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return target.defendSpell(damage);
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
