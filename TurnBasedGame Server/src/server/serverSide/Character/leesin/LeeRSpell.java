package server.serverSide.Character.leesin;
import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;

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
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
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
