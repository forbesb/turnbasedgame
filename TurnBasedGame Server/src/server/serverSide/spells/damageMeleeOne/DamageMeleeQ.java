package server.serverSide.spells.damageMeleeOne;

import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;

public class DamageMeleeQ extends Spell{

	public DamageMeleeQ(Character caster) {
		super(false, true, true, caster);
		setName("Slow");
		setRange(5);
		setMaxCD(4);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean activeEffect(Character target) {
		if (getCaster().equals(target)){
			//TODO: Attack Enhancers
			//bonus damage thing?
			//do I need like... an AttackEnhancer list too?
		} else {
			//TODO: this? seems not to work
			EffectTimer t = new EffectTimer(2, target, EffectTimer.SPEED, -2);
			target.addEffect(t); //slow by two for two turns
		}
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
