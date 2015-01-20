package Character.lissandra;

import game.Character;
import game.DamageModifier;
import game.EffectTimer;
import game.Poison;
import spells.Spell;

public class LissRSpell extends Spell{

	public LissRSpell(Character c) {
		super(false, true, false, c);
		setRange(getCaster().getRange());
		setMaxCD(10);
		setName("Frozen Tomb");
		setOnCD();
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new EffectTimer(3,target,EffectTimer.SNARE,0));
		target.addEffect(new EffectTimer(3,target,EffectTimer.SILENCE,0));
		target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 5, new DamageModifier(4, gui.Game.fake, 1.5));
		target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 5, new Poison(4, gui.Game.fake, getCaster().getAttack()*2/5));
		target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 5, new EffectTimer(4, gui.Game.fake, EffectTimer.SPEED, -3));
		target.addEffect(new DamageModifier(4, target, 1.5));
		target.addEffect(new Poison(4, target, getCaster().getAttack()*2/5));
		target.addEffect(new EffectTimer(4, target, EffectTimer.SPEED, -3));
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
