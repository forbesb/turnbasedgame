package Character.taric;
import game.Character;
import spells.Spell;
public class TaricQSpell extends Spell{

	public TaricQSpell(Character c) {
		super(false, true, true, c);
		setRange(5);
		setMaxCD(4);
		setName("Gem Heal");
	}

	@Override
	public boolean activeEffect(Character target) {
		if (!target.equals(getCaster()))
			target.heal(150);
		getCaster().heal(300);
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
