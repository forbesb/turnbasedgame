package server.serverSide.Character.hongyu;


import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;
public class HongyuESpell extends Spell{

	public HongyuESpell(Character c) {
		super(false,true,false, c);
		setRange(getCaster().getRange());
		setMaxCD(4);
		setName("Screeching Laugh");
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new EffectTimer(3, target, EffectTimer.SILENCE, 0));
		target.addEffect(new EffectTimer(3, target, EffectTimer.SNARE, 0));
		target.addEffect(new EffectTimer(3, target, EffectTimer.DEFENCE, +50));
		target.addEffect(new EffectTimer(3, target, EffectTimer.MAGICDEFENCE, +50));
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
