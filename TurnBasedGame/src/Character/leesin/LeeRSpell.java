package Character.leesin;
import game.Character;
import game.EffectTimer;
import spells.Spell;
public class LeeRSpell extends Spell{

	public LeeRSpell(Character c) {
		super(false, true, false, c);
		this.setRange(1);
		this.setName("\"Weak\" kick");
		this.setMaxCD(10);
		setOnCD();
	}

	@Override
	public boolean activeEffect(Character target) {
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		target.addEffect(new EffectTimer(2,target,EffectTimer.SILENCE,0));
		target.addEffect(new EffectTimer(2,target,EffectTimer.SNARE,0));
		return target.defendSpell(target.getCurrentHP());
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
