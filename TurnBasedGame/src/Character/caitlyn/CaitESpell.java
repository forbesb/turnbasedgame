package Character.caitlyn;

import game.Character;
import game.EffectTimer;
import spells.Spell;
public class CaitESpell extends Spell{

	public CaitESpell(Character c) {
		super(false,true,false, c);
		setRange(getCaster().getRange());
		setMaxCD(7);
		setName("Stun Shot");
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new EffectTimer(2, target, EffectTimer.SILENCE,0));
		target.addEffect(new EffectTimer(2, target, EffectTimer.SNARE,0));
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return target.defendSpell(getCaster().getAttack());
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
