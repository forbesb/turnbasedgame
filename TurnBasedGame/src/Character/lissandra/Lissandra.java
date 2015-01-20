package Character.lissandra;
import javax.swing.ImageIcon;
import spells.Spell;

import game.Character;
import gui.ConsoleBox;
public class Lissandra extends Character{
	private int lastMoved = 0;
	public Lissandra(int x, int y, String t,  ConsoleBox c) {
		super(1200, 70, 50, 50, 7, 3, x, y, t, "Lissandra", c, new ImageIcon("Lissandra.png"), new ImageIcon("LissandraIcon.png"));
		this.setSpell(new Spell [] {new LissQSpell(this), new LissWSpell(this), new LissESpell(this), new LissRSpell(this)});
	}
	public void tick(){
		super.tick();
		setRange(7);
		if (lastMoved%2 == 0 && lastMoved != 0){
			//gui.Game.console.println("Lissandra has unlocked her potential. Her W, E, and R have become global.");
			for (int i = 1; i < 4; i ++){
				getSpell(i).setRange(-1);
			}
		}else{
			for (int i : new int[]{1,3}){
				getSpell(i).setRange(getRange());
			}
			getSpell(2).setRange(getRange()+2);
		}
			
		if (isMoved()){
			lastMoved = 0;
		}else{
			lastMoved++;
		}
	}
}
