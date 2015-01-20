package Character.vlassali;

import game.Character;
import game.SigilOfSilence;
import spells.Spell;

public class VladQSpell extends Spell{

	public VladQSpell(Character caster) {
		super(false, true, false, caster);
		setName("Sigil of Silence");
		setMaxCD(3);
		setRange(getCaster().getRange());
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new SigilOfSilence(target));
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		getCaster().heal(getCaster().getAttack()*100/target.getMagicres()*1/5);
		return target.defendSpell(getCaster().getAttack());
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
