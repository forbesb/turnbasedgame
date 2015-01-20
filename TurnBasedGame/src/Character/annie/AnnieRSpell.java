package Character.annie;
import game.AoEDmg;
import game.Character;
import game.EffectTimer;
import spells.Spell;
public class AnnieRSpell extends Spell{

	public AnnieRSpell(Character c) {
		super(false, true, false, c);
		setMaxCD(10);
		setRange(-1);
		setOnCD();
		this.setName("Destroy");
	}

	@Override
	public boolean activeEffect(Character target) {
		gui.Game.console.println(getCaster().getName() + " used "+"Destroy"+" on "+ target);
		target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 3, new EffectTimer(2,gui.Game.fake,EffectTimer.SNARE, 0));
		target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 3, new AoEDmg("Destroy",getCaster().getAttack()));
		if (getCaster().getSpellCasts() == 2){
			target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 3, new EffectTimer(2,gui.Game.fake,EffectTimer.SILENCE, 0));
			
		}
		getCaster().addSpellCasts();
		
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
