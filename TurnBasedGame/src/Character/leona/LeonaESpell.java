package Character.leona;
import game.Character;
import game.EffectTimer;
import spells.Spell;
public class LeonaESpell extends Spell{

	public LeonaESpell(Character c) {
		super(false, true, false, c);
		setMaxCD(3);
		setName("Shield of Daybreak");
		setRange(1);
	}

	@Override
	public boolean activeEffect(Character target) {
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		target.addEffect(new EffectTimer(2,target,EffectTimer.SILENCE,0));
		target.addEffect(new EffectTimer(2,target,EffectTimer.SNARE,0));
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
