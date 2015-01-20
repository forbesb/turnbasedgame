package server.serverSide.Character.caitlyn;

import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;
import server.serverSide.game.Poison;
public class CaitWSpell extends Spell{

	public CaitWSpell(Character c) {
		super(false,true,false, c);
		setRange(getCaster().getRange());
		setMaxCD(4);
		setName("Poison Shot");
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new Poison(3, target, 100));
		target.addEffect(new EffectTimer(3, target, EffectTimer.SPEED,-2));
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		return target.defendSpell(getCaster().getAttack());
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
