package Character.brian;
import game.Character;
import game.EffectTimer;
import spells.Spell;
public class BrianESpell extends Spell{

	public BrianESpell(Character c) {
		super(false, true, false, c);
		this.setRange(7);
		this.setName("Initiate RunningBack");
		this.setMaxCD(8);
	}

	@Override
	public boolean activeEffect(Character target) {
		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		int [] arr = {-1,1};
		boolean entered = false;
		int cY = target.getX();
		int cX = target.getY();
		for (int i : arr){
			for (int j : arr){
				if (getCaster().getMap().spaceEmpty(cY+i, cX+j) && !entered){
					getCaster().getMap().move(getCaster(), cY+i, cX+j);
					entered = true;
				}
				
			}
		}
		target.addEffect(new EffectTimer(3,target,EffectTimer.SILENCE,0));
		return false;
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
