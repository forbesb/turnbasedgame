package Character.lissandra;
import game.Character;
import game.EffectTimer;
import spells.Spell;
public class LissESpell extends Spell{

	public LissESpell(Character c) {
		super(false, true, false, c);
		setMaxCD(3);
		setRange(getCaster().getRange()+2);
		setName("FrostBite");
	}

	@Override
	public boolean activeEffect(Character target) {
		for (EffectTimer i : target.getEffs()){
			if (i.toString().equals("Hypothermia")){
				target.addEffect(new EffectTimer(2, target, EffectTimer.SNARE,0));
				gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
				return target.defendSpell(getCaster().getAttack()*5/2);
			}
		}
		return target.defendSpell(getCaster().getAttack()*3/2);
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
