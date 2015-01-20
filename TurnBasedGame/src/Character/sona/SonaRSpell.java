package Character.sona;

import game.Character;
import game.EffectTimer;
import spells.Spell;

public class SonaRSpell extends Spell{

	public SonaRSpell(Character c) {
		super(false, true, false, c);
		setName("Maven's Kiss");
		setRange(-1);
		setMaxCD(10);
		setOnCD();
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new EffectTimer(2,target,EffectTimer.DEFENCE,(int)10E7));
		target.addEffect(new EffectTimer(2,target,7,(int)10E7));
		target.addEffect(new EffectTimer(2,target,EffectTimer.HP,100));
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
