package Character.vlassali;
import javax.swing.ImageIcon;

import spells.Spell;
import game.Character;
import game.EffectTimer;
import gui.ConsoleBox;
public class Vladimir extends Character{
	public Vladimir(int x, int y, String t,  ConsoleBox c) {
		super(1200, 300, 50, 50, 5, 3, x, y, t, "Vladimir", c, new ImageIcon("vlassali.png"), new ImageIcon("VladimirIcon.png"));
		
		
		
		setSpell(new Spell[]{new VladQSpell(this),  new VladWSpell(this), new VladESpell(this), new VladRSpell(this)});
		
	}
	public boolean attack(Character defender){
		if (inRange(defender) && !getTeam().equals(defender.getTeam())){
			//makes sure attack is possible and the team is correct
			setAttacked(true);
			boolean swag= false;
			for (EffectTimer eff : defender.getEffs()){
				if ((eff+"").equals("SigilOfSilence")){
					swag = true;
				}
			}
			if (swag){
				defender.defendSpell(500);
				defender.addEffect(new EffectTimer(2,defender,EffectTimer.SILENCE,0));
			}
			return defender.defend(this); //call defence method of target
		}
		return false;
	}
}
