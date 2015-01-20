package server.serverSide.Character.annie;
import server.serverSide.game.AoEDmg;
import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;
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
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+"Destroy"+" on "+ target);
		target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 3, new EffectTimer(2,server.serverSide.game.Fake.fake,EffectTimer.SNARE, 0));
		target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 3, new AoEDmg("Destroy",getCaster().getAttack()));
		if (getCaster().getSpellCasts() == 2){
			target.getMap().effectTimerAllInRange(target.getY(), target.getX(), getCaster().getTeam(), 3, new EffectTimer(2,server.serverSide.game.Fake.fake,EffectTimer.SILENCE, 0));
			getCaster().resetSpellCasts(); //TODO: no??
		}
		getCaster().addSpellCasts();
		
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}
