package server.serverSide.Character.sona;
import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;

public class SonaWSpell extends Spell{

	public SonaWSpell(Character c) {
		super(false, true, true, c);
		setRange(5);
		setMaxCD(3);
		setName("Maven's Song");
	}

	@Override
	public boolean activeEffect(Character target) {
		if (!target.equals(getCaster()))
			target.heal(300);
		getCaster().heal(300);
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		target.addEffect(new EffectTimer(3,target,EffectTimer.DEFENCE,50));
		target.addEffect(new EffectTimer(3,target,EffectTimer.MAGICDEFENCE,50));
		getCaster().addEffect(new EffectTimer(3,target,EffectTimer.DEFENCE,50));
		getCaster().addEffect(new EffectTimer(3,target,EffectTimer.MAGICDEFENCE,50));
		target.addEffect(new EffectTimer(3,target,EffectTimer.ATTACK, 50));
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
