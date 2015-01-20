package Character.hongyu;


import game.Character;
import game.EffectTimer;
import spells.Spell;
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
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
