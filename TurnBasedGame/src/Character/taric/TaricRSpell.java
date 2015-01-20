package Character.taric;
import game.AoEDmg;
import game.Character;
import game.EffectTimer;
import spells.Spell;
public class TaricRSpell extends Spell{

	public TaricRSpell(Character c) {
		super(false, true, true, c);
		setMaxCD(5);
		setRange(0);
		setOnCD();
		this.setName("Radiance");
	}

	@Override
	public boolean activeEffect(Character target) {
		String [] teams = {"A","B"};
		int team = 0;
		if (teams[0].equals(getCaster().getTeam())){
			team = 1;
		}
		target.getMap().effectTimerAllInRange(getCaster().getY(), getCaster().getX(), teams[team], 4, new EffectTimer(5,gui.Game.fake,EffectTimer.ATTACK, 50));
		target.getMap().effectTimerAllInRange(getCaster().getY(), getCaster().getX(), teams[team], 4, new EffectTimer(5,gui.Game.fake,EffectTimer.DEFENCE, 50));
		target.getMap().effectTimerAllInRange(getCaster().getY(), getCaster().getX(), teams[team], 4, new EffectTimer(5,gui.Game.fake,EffectTimer.MAGICDEFENCE, 50));
		team = 1;
		if (teams[0].equals(getCaster().getTeam())){
			team = 0;
		}
		target.getMap().effectTimerAllInRange(getCaster().getY(), getCaster().getX(), teams[team], 4, new EffectTimer(5,gui.Game.fake,EffectTimer.ATTACK, -50));
		target.getMap().effectTimerAllInRange(getCaster().getY(), getCaster().getX(), teams[team], 4, new EffectTimer(5,gui.Game.fake,EffectTimer.DEFENCE, -50));
		target.getMap().effectTimerAllInRange(getCaster().getY(), getCaster().getX(), teams[team], 4, new EffectTimer(5,gui.Game.fake,EffectTimer.MAGICDEFENCE, -50));
		target.getMap().effectTimerAllInRange(getCaster().getY(), getCaster().getX(), teams[team], 4, new AoEDmg("Radiance",getCaster().getAttack()));
		gui.Game.console.println(getCaster().getName() + " used "+getName());
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		return false;
	}
	
}