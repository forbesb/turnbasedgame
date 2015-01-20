package server.serverSide.Character.taric;

import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;
public class TaricESpell extends Spell{

	public TaricESpell(Character c) {
		super(false,true,false, c);
		setRange(getCaster().getRange());
		setMaxCD(7);
		setName("Dazzle");
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new EffectTimer(2, target, EffectTimer.SILENCE,0));
		target.addEffect(new EffectTimer(2, target, EffectTimer.SNARE,0));
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		return target.defendSpell(getCaster().getAttack());
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
