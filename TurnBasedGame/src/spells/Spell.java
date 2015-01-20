package spells;

import java.util.Hashtable;

import game.*;
import game.Character;

public abstract class Spell {
	private boolean hasPassive;
	private boolean hasActive;
	private boolean selfCastable;
	private int cooldown = 0;
	private int maxcd;
	private String name;
	
	private int range;
	private Character caster;

	public Spell(boolean p, boolean a, boolean s, Character c) {
		hasPassive = p;
		hasActive = a;
		setSelfCastable(s);
		caster = c;
	}


	public abstract boolean activeEffect(Character target);
	public abstract boolean passiveEffect(Character target);
	
	public Hashtable<String, Terrain> getTilesInRange(Map m){
		Hashtable<String, Terrain> ht = new Hashtable<>(); //get spaces in range of the spell
		if (range == -1){
			m.returnAllSpaces(ht);
			return ht;
		} else {
			m.getAttacks(caster.getX(), caster.getY(), range, caster.getTeam(), ht);
		}
		return ht;
	}
	
	//getters and setters
	public Character getCaster() {
		return caster;
	}

	public void setCaster(Character caster) {
		this.caster = caster;
	}
	
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}
	
	public int getMaxCD() {
		return maxcd;
	}

	public void setMaxCD(int cooldown) {
		this.maxcd = cooldown;
	}
	
	public void tickCD(){
		if (cooldown >0)
			cooldown--;
	}
	
	public void setOnCD(){
		cooldown=maxcd;
	}
	
	public boolean offCD(){
		return cooldown==0;
	}
	
	public boolean inRange(Character target){
		//simple inrange test without pathfinding
		if (Math.abs(caster.getX()-target.getX()) + Math.abs(caster.getY() - target.getY()) <=range){
			return true;
		}
		return false;
	}
	
	public int getId(){
		for (int i = 0; i<4; i++){
			if (caster.getSpell(i) == this){
				return i; //find spell id from caster
			}
		}
		return -1;
		
	}


	public boolean isSelfCastable() {
		return selfCastable;
	}


	public void setSelfCastable(boolean selfCastable) {
		this.selfCastable = selfCastable;
	}


}
