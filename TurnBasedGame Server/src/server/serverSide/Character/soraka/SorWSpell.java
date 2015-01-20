package server.serverSide.Character.soraka;
import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;
public class SorWSpell extends Spell{

	public SorWSpell(Character c) {
		super(false, true, true, c);
		setMaxCD(3);
		setRange(getCaster().getRange());
		setName("Armor Of Gods");
	}

	@Override
	public boolean activeEffect(Character target) {
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		target.addEffect(new EffectTimer(3,target,EffectTimer.DEFENCE,target.getDefence()*1/2));
		target.addEffect(new EffectTimer(3,target,EffectTimer.MAGICDEFENCE,target.getMagicres()*1/2));
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
