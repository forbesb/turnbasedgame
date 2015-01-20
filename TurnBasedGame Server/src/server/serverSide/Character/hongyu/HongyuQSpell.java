package server.serverSide.Character.hongyu;


import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;
import server.serverSide.game.Poison;

public class HongyuQSpell extends Spell{

	public HongyuQSpell(Character c) {
		super(false,true,false, c);
		setRange(getCaster().getRange());
		setMaxCD(4);
		setName("Mocking Laugh");
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new Poison(3, target, 300));
		target.addEffect(new EffectTimer(3, target, EffectTimer.ATTACK,-100));
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
