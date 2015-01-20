package Character.brian;
import game.Character;
import game.EffectTimer;
import game.Poison;
import spells.Spell;
public class BrianQSpell extends Spell{

	public BrianQSpell(Character c) {
		super(false, true, false, c);
		setName("Brian's Diplomacy");
		setRange(1);
		setMaxCD(2);
	}

	@Override
	public boolean activeEffect(Character target) {
		target.addEffect(new Poison(4,target,getCaster().getAttack()/2));
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		for (EffectTimer i : target.getEffs()){
			if (i.toString().equals("DeathMark")){
				i.addDamage(getCaster().getAttack()*6/5);
			}
		}
		return target.defendSpell(getCaster().getAttack()*6/5);
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
