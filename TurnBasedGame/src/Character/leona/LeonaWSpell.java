package Character.leona;
import game.Character;
import game.EffectTimer;
import spells.Spell;
public class LeonaWSpell extends Spell{

	public LeonaWSpell(Character c) {
		super(false, true, true, c);
		setMaxCD(5);
		setName("Eclipse");
		setRange(0);
	}

	@Override
	public boolean activeEffect(Character target) {
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		target.addEffect(new EffectTimer(3,target,EffectTimer.MAGICDEFENCE,target.getMagicres()*5/4));
		target.addEffect(new EffectTimer(3,target,EffectTimer.DEFENCE,target.getDefence()*5/4));
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
