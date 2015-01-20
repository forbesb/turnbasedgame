package Character.vlassali;

import game.Character;
import game.EffectTimer;
import game.UnAffectable;
import spells.Spell;

public class VladRSpell extends Spell{

	public VladRSpell(Character caster) {
		super(false, true, true, caster);
		setName("BlackKingBar");
		setMaxCD(10);
		setRange(0);
		setOnCD();
	}

	@Override
	public boolean activeEffect(Character target) {
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		target.addEffect(new EffectTimer(5, target, 7, (int)10E8));
		target.addEffect(new UnAffectable(5, target));
		
		return false;
		
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
