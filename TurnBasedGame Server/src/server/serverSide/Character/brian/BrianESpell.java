package server.serverSide.Character.brian;
import server.serverSide.game.Character;
import server.serverSide.game.EffectTimer;
import server.serverSide.spells.Spell;
public class BrianESpell extends Spell{

	public BrianESpell(Character c) {
		super(false, true, false, c);
		this.setRange(7);
		this.setName("Initiate RunningBack");
		this.setMaxCD(8);
	}

	@Override
	public boolean activeEffect(Character target) {
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		int [] arr = {-1,1};
		boolean entered = false;
		int cY = target.getX();
		int cX = target.getY();
		for (int i : arr){
			for (int j : arr){
				if (getCaster().getMap().spaceEmpty(cY+i, cX+j) && !entered){
					getCaster().getMap().move(getCaster(), cY+i, cX+j);
					getCaster().getThread().sendMove(getCaster(), getCaster().getMap().getTerrain(cY+i,  cX+j));
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
