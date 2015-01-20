package Character.leesin;
import game.Character;
import game.EffectTimer;
import game.SuperHeal;
import spells.Spell;
public class LeeQSpell extends Spell{

	public LeeQSpell(Character c) {
		super(false, true, true, c);
		this.setRange(0);
		this.setName("Iron Will");
		this.setMaxCD(5);
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new SuperHeal(target,4/10*target.getHp()));
		target.addEffect(new EffectTimer(2,target,EffectTimer.DEFENCE,(int)10E7));
		target.addEffect(new EffectTimer(2,target,7,(int)10E7));
		target.addEffect(new EffectTimer(2, target, EffectTimer.SNARE, 0));
		target.addEffect(new EffectTimer(2, target, EffectTimer.SILENCE, 0));
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
