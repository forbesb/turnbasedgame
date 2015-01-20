package server.serverSide.Character.caitlyn;
import javax.swing.ImageIcon;
import server.serverSide.spells.Spell;
import server.serverSide.game.Character;


public class Caitlyn extends Character{
	public Caitlyn(int x, int y, String t) {
		super(1200, 700, 50, 50, 10, 1, x, y, t, "Caitlyn");
		//TODO don't be bear.png
		setSpell(new Spell[]{new CaitQSpell(this),new CaitWSpell(this), new CaitESpell(this), new CaitRSpell(this)});
		
	}
	public boolean defend(Character attacker){
		int damage = attacker.getAttack();
		
		double denom = 100+getDefence()+getSpace().getDef();
		double dmg = 100*damage/denom; //damage formula
		
		if (getpShieldCount() <= 0)
			takeDamage((int)(dmg*2)); //same as below, but with physical shield
		else {
			setpShieldCount(getpShieldCount() - 1);
			//con.println(name+" defended the attack with a shield");
		}
		return true;
	}
	
	public boolean defendSpell(int damage){

		double denom = 100+getMagicres();
		double dmg = 100*damage/denom; //damage formula
		
		if (getsShieldCount() <= 0) //if no shield, take damage
			takeDamage((int)dmg*2);
		else { //if shield, destroy shield, no damage
			setsShieldCount(getsShieldCount() - 1);
			//con.println(name+" defended the attack with a shield");
		}
		
		
		//takeDamage((int)dmg);
		return true;
	}
}
