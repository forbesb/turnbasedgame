package Character.leona;
import game.AoEDmg;
import game.Character;
import game.EffectTimer;
import game.Poison;
import spells.Spell;
public class LeonaRSpell extends Spell{

	public LeonaRSpell(Character c) {
		super(false, true, false, c);
		setMaxCD(5);
		setRange(10);
		setOnCD();
		this.setName("Solar Flare");
	}

	@Override
	public boolean activeEffect(Character target) {
		target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 3, new EffectTimer(3,gui.Game.fake,EffectTimer.SNARE, 0));
		target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 3, new AoEDmg("Solar Flare", getCaster().getAttack()));
		target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 3, new EffectTimer(3,gui.Game.fake,EffectTimer.SILENCE, 0));
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
