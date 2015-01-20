package Character.tryndamere;

import spells.Spell;
import game.Character;
import game.EffectTimer;

public class DamageMeleeQ extends Spell{

	public DamageMeleeQ(Character caster) {
		super(false, true, false, caster);
		setName("Slow");
		setRange(5);
		setMaxCD(4);
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new EffectTimer(2,target,EffectTimer.SILENCE,0));
		target.addEffect(new EffectTimer(2,target,EffectTimer.SNARE,0));
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return target.defendSpell(getCaster().getAttack()*1/2);
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
