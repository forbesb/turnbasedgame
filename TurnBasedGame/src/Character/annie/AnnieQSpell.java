package Character.annie;
import game.Character;
import game.EffectTimer;
import spells.Spell;
public class AnnieQSpell extends Spell{

	public AnnieQSpell(Character c) {
		super(false, true, false, c);
		setMaxCD(5);
		setRange(c.getRange());
		this.setName("Null Sphere");
		
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new EffectTimer(2, target, EffectTimer.SILENCE,0));
		if (getCaster().getSpellCasts() == 2){
			target.addEffect(new EffectTimer(2, target, EffectTimer.SNARE,0));
		}
		getCaster().addSpellCasts();
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return target.defendSpell(getCaster().getAttack());
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
