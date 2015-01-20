package server.serverSide.Character.vlassali;

import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;

public class VladWSpell extends Spell{

	public VladWSpell(Character caster) {
		super(false, true, true, caster);
		setName("Troll Pool");
		setMaxCD(10);
		setRange(0);
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new EffectTimer(2,target,EffectTimer.DEFENCE,(int)10E7));
		target.addEffect(new EffectTimer(2,target,7,(int)10E7));
		target.addEffect(new EffectTimer(2, target, EffectTimer.SNARE, 0));
		target.addEffect(new EffectTimer(2, target, EffectTimer.SILENCE, 0));
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		return target.takeDamage(target.getCurrentHP()*1/5);
		
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
