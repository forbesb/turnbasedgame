package Character.sona;
import game.Character;
import game.EffectTimer;
import spells.Spell;
public class SonaQSpell extends Spell{

	public SonaQSpell(Character c) {
		super(false, true, false, c);
		setName("Maven's Shield");
		setRange(5);
		setMaxCD(3);
	}

	@Override
	public boolean activeEffect(Character target) {
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		target.addPhysShield();
		target.addSpellShield();
		target.addEffect(new EffectTimer(3,target,EffectTimer.ATTACK, 50));
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
