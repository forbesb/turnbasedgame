package Character.lissandra;

import game.Character;
import game.EffectTimer;
import spells.Spell;

public class LissQSpell extends Spell{
	public LissQSpell(Character c) {
		super(false, true, true, c);
		setName("Blood Boil");
		setMaxCD(3);
		setRange(0);
	}
	
	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new EffectTimer(4,target,EffectTimer.ATTACK,100));
		target.addEffect(new EffectTimer(4,target,EffectTimer.SPEED,3));
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}

}
