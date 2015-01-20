package server.serverSide.Character.leona;
import server.serverSide.game.AoEDmg;
import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.game.Poison;
import server.serverSide.spells.Spell;
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
		target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 3, new EffectTimer(3,server.serverSide.game.Fake.fake,EffectTimer.SNARE, 0));
		target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 3, new AoEDmg("Solar Flare", getCaster().getAttack()));
		target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 3, new EffectTimer(3,server.serverSide.game.Fake.fake,EffectTimer.SILENCE, 0));
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
