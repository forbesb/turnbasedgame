package Character.leona;
import game.Character;
import game.EffectTimer;
import spells.Spell;
public class LeonaQSpell extends Spell{

	public LeonaQSpell(Character c) {
		super(false, true, false, c);
		setRange(7);
		setMaxCD(5);
		setName("Zenith Blade");
	}

	@Override
	public boolean activeEffect(Character target) {
		boolean entered = false;
		int cY = target.getX();
		int cX = target.getY();

		gui.Game.console.println(getCaster().getName() + " used "+getName()+" on "+ target);
		int [] arr = {-1,1};
		for (int i : arr){
			for (int j : arr){
				if (getCaster().getMap().spaceEmpty(cY+i, cX+j) && !entered){
					getCaster().getMap().move(getCaster(), cY+i, cX+j);
					entered = true;
				}
				
			}
		}
		if (target.getTeam().equals(getCaster().getTeam())){
			target.addPhysShield();
			target.addSpellShield();
			return entered;
		}else{
			target.addEffect(new EffectTimer(3,target,EffectTimer.SPEED,-3));
			return target.defendSpell(getCaster().getAttack());
		}
		

	}

	@Override
	public boolean passiveEffect(Character target) {
		// TODO Auto-generated method stub
		return false;
	}

}
