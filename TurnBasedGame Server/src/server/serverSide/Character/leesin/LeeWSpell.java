package server.serverSide.Character.leesin;
import server.serverSide.game.Character;
import server.serverSide.spells.Spell;
public class LeeWSpell extends Spell{
	public LeeWSpell(Character c) {
		super(false, true, false, c);
		setMaxCD(7);
		setName("Sonic Wave");
		setRange(7);
	}

	@Override
	public boolean activeEffect(Character target) {
		getCaster().getThread().sendConsole(getCaster().getName() + " used "+getName()+" on "+ target);
		boolean entered = false;
		int cY = target.getX();
		int cX = target.getY();
		int [] arr = {-1,1};
		for (int i : arr){
			for (int j : arr){
				if (getCaster().getMap().spaceEmpty(cY+i, cX+j) && !entered){
					getCaster().getMap().move(getCaster(), cY+i, cX+j);
					getCaster().getThread().sendMove(getCaster(), getCaster().getMap().getTerrain(cY+i, cX+j));
					entered = true;
				}
				
			}
		}
		return target.takeDamage(getCaster().getAttack());
	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}
}
