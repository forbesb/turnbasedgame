package game;

public class EffectTimer {
	public static int HP = 0;
	public static int ATTACK = 1;
	public static int DEFENCE = 2;
	public static int RANGE = 3;
	public static int SPEED = 4;
	public static int SNARE = 5;
	public static int SILENCE = 6;
	public static int MAGICDEFENCE = 7;
	public static int NONE = -1;
	private static String [] effects = new String []{"Hp","Attack","Defence","Range","Speed","Snare","Silence","MagicDefence","SNARE","SILENCE","MAGICDEFENSE"};
	private int hp = 0, attack = 0, defence = 0, range = 0, speed= 0, magicres = 0;
	boolean silence = false, snare = false;

	private int time;//length
	private Character chara; //effected character
	private int effect; //stat affected
	private int am;
	
	public EffectTimer(int length, Character c, int eff, int amount){
		//TODO: Deal with stacking (getSource + stackable boolean?)
		time = length;
		chara = c;
		effect = eff;
		am = amount;
		
		switch(eff){ //basic effect timer changes a stat. static variables help with this
			case 0:
				hp = amount;
				chara.addHp(amount);
				break;
			case 1:
				attack = amount;
				chara.addAttack(amount);
				break;
			case 2:
				defence = amount;
				chara.addDefence(amount);
				break;
			case 3:
				range = amount;
				chara.addRange(amount);
				break;
			case 4:
				speed = amount;
				chara.addSpeed(amount);
				break;
			case 5:
				chara.setSnared(true);
				chara.setMoved(true);
				snare = true;
				break;
			case 6:
				chara.setSilenced(true);
				chara.setAttacked(true);
				silence = true;		
				break;
			case 7:
				magicres = amount;
				chara.addMagicRes(amount);
				break;
			case -1:
				break;
		}
	}
	
	public int getTime(boolean decrease) {
		if (decrease){ //dunno what I used this for tbh
			return --time;
		}
		return time;
	}

	public Character getCharacter() {
		return chara;
	}

	public void setCharacter(Character chara) {
		this.chara = chara;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public boolean tick(){
		//for one turn, start time as 1
		//tick at start of each turn, so things will reset at the beginning of the turn after they're made 
		//(if counter is 1). 
		if (--time >0){
			return false;
		}
		reset();
		return true;
	}
	
	private void reset(){
		chara.addHp(hp*-1);
		chara.addAttack(attack*-1);
		chara.addDefence(defence*-1); //returns the amount taken off
		chara.addRange(range*-1);
		chara.addSpeed(speed*-1);
		chara.addMagicRes(magicres*-1);
		if (snare) chara.setSnared(false);
		if (silence) chara.setSilenced(false);
	}
	
	public EffectTimer copy(Character c){
		EffectTimer e = new EffectTimer(time, c, effect, am);
		return e;
	}
	public void addDamage(int damage){
	}
	
	public String toString(){
		  switch(effect){ //basic effect timer changes a stat. static variables help with this
		   case 0:
		    return "Hp Change: "+am;
		   case 1:
		    return "Attack Change "+am;
		   case 2:
		    return "Defence Change "+am;
		   case 3:
		    return "Range Change "+am;
		   case 4:
		    return "Speed Change "+am;
		   case 5:
		    return "Snared";
		   case 6:
		    return "Silenced"; 
		   case 7:
		    return "Magic Res Change"+am;
		  }
		  return "Special Effect";
		 }
}
