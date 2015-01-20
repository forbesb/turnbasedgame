package server.serverSide.Character.taric;

import server.serverSide.game.AoEDmg;
import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;

public class TaricWSpell extends Spell{

	public TaricWSpell(Character c) {
		super(false, true, true, c);
		setRange(0);
		setName("Shatter");
		setMaxCD(4);
	}

	@Override
	public boolean activeEffect(Character target) {
		
		
		target.getMap().effectTimerAllInRange(getCaster().getX(), getCaster().getY(), getCaster().getTeam(), 2, new EffectTimer(2,server.serverSide.game.Fake.fake,EffectTimer.DEFENCE, -100));
		target.getMap().effectTimerAllInRange(getCaster().getX(), getCaster().getY(), getCaster().getTeam(), 2, new AoEDmg(getName(),getCaster().getAttack()));
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName());
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
