package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import gui.*;

import javax.swing.ImageIcon;

import spells.FakeSpell;
import spells.Spell;
import spells.trondoFiora.*;
//this summative will never use java.lang.Character
//trust
public class Character {
	private int spellCasts = 0;
	private int hp, chp, attack, defence, magicres, range, speed, x, y; //stats and position
	private long id; //for server stuff mostly
	private boolean moved = false, attacked = false, silenced = false, snared = false; //action booleans
	private static long baseid = 0; //make sure no id repeats
	private String team, name; //team and name
	private Terrain space; //current square
	private Spell[] spells = new Spell[4]; //list of spells 
	private int sShieldCount = 0, pShieldCount = 0;//physical and spell shield counts
	private ArrayList<EffectTimer> effs = new ArrayList<>(); //temporary effect list
	private ConsoleBox con; //console to print to
	private ImageIcon img, icon; // image and icon
	private Map map;
	private double modifier = 1;
	
	public Character(int h, int a, int d, int m, int r, int sp, int x, int y, String t, String s, ConsoleBox c, ImageIcon sprite, ImageIcon icon){
		hp = h;
		chp = h;
		attack = a;
		defence = d;
		magicres = m;
		range = r;   //setting important values
		speed = sp;
		team = t;
		name = s;
		this.x = x;
		this.y = y;
		con = c;
		img = sprite;
		this.icon = icon;
		id = baseid++;
		
		//TEMPORARY:
		spells[0] = new DamageMeleeQ(this);
		//spells[1] = new DamageMeleeW(this);
		spells[1] = new FakeSpell(false, true, false, this);
		spells[2] = new DamageMeleeE(this);//adds spells - these will be changed depending on character
		spells[3] = new DamageMeleeR(this);
		//END TEMPORARY
		
	}
	
	//HONGYU WORRY ABOUT THIS (and by worry I mean override when extending character) vvvv
	
	public boolean attack(Character defender){
		if (inRange(defender) && !team.equals(defender.getTeam())){
			//makes sure attack is possible and the team is correct
			con.println(this.name+" attacked "+defender.getName());
			attacked = true;
			return defender.defend(this); //call defence method of target
		}
		return false;
	}
	
	public boolean attackSpell(int spellIndex, Character defender){
		if (spells[spellIndex] != null){ //called to use a spell...
			//pretty sure I was supposed to use this somewhere and I didn't
			//actually didn't I think just ignore this
			//TODO: look in parseAction and change to this
			Spell cast = spells[spellIndex];
			cast.activeEffect(defender);
			return true;
		}
		
		return true;
	}
	
	public boolean defend(Character attacker){
		int damage = attacker.attack;
		
		double denom = 100+defence+space.getDef();
		double dmg = 100*damage/denom*modifier; //damage formula
		
		if (pShieldCount <= 0)
			takeDamage((int)dmg); //same as below, but with physical shield
		else {
			pShieldCount--;
			con.println(name+" defended the attack with a shield");
		}
		return true;
	}
	
	public boolean defendSpell(int damage){

		double denom = 100+magicres;
		double dmg = 100*damage/denom*modifier; //damage formula
		
		if (sShieldCount <= 0) //if no shield, take damage
			takeDamage((int)dmg);
		else { //if shield, destroy shield, no damage
			sShieldCount--;
			//con.println(name+" defended the attack with a shield");
		}
		
		
		//takeDamage((int)dmg);
		return true;
	}
	
	public boolean inRange(Character other){
		if (Math.abs(x-other.getX()) + Math.abs(y - other.getY()) <=range){
			return true; //simple non-pathfinding test for inrange
		}
		return false;
	}
	
	public boolean heal(int h){
		chp+=h;
		if (chp>hp)
			chp=hp;//making sure it doesn't pass max hp
		return true;
	}
	
	public void tick(){
		//this is called EVERY TURN
		if  (!snared)   setMoved(false); else setMoved(true);
		if (!silenced ) setAttacked(false); else setAttacked(true);
		
		for (Spell sp: spells){
			sp.tickCD();
		}
		//resets attack and movement
		ArrayList<EffectTimer> completed = new ArrayList<>();
		for (EffectTimer t: effs){ //ticks temporary effects as well
			if (t.tick())
				completed.add(t);
		}
		for (EffectTimer t: completed){
			effs.remove(t);//removes completed effects
		}
	}
	
	public boolean takeDamage(int damage){
		chp-=(int)damage;//directly take damage, either bypassing defend methods or to be used by them
		return true;
	}
	
	//HONGYU WORRY ABOUT THIS (and by worry I mean override when extending character)^^^^
	
	public void drawSelf(Graphics g, int x, int y, int[] tilesize){
		if  (img == null){ //same as below
			ImageIcon img = new ImageIcon("bear.png");
		}
		//simple draw method call
		if (team.equals("A")) g.setColor(new Color(255,0,0));
		else g.setColor(new Color(0,0,255));
		g.drawOval(x, y, tilesize[0], tilesize[1]);
		g.drawImage(img.getImage(), x, y, tilesize[0], tilesize[1], null);
	}
	
	public void drawIcon(Graphics g, int x, int y, int[] tilesize){
		if (icon==null){
			if (img==null){ //making sure it works if things are null, which should never happen
				ImageIcon img = new ImageIcon("bear.png");
			}
			ImageIcon icon = img;
		}
		//simple draw method call
		g.drawImage(icon.getImage(), x, y, tilesize[0], tilesize[1], null);
	}
	
	public void drawHealthBar(Graphics g, int x, int y, int length, int height){
		double percent = (double)chp/hp;
		g.setColor(new Color(0,0,0));
		g.drawRect(x, y, length, height); //draw black border
		if (percent>0.6)
			g.setColor(new Color(0,255,0));
		else if (percent>0.3)   //changes color based on percentage
			g.setColor(new Color(255,200,0));
		else
			g.setColor(new Color(255,0,0));
		g.fillRect(x+1, y+1, (int)(length*percent)-1, height-1);//this makes it in the black border
		
	}
	
	public boolean intersects(int mx, int my, int[] tilesize){
		//simple intersection if
		if (mx >x*tilesize[0] && mx < (x+1)*tilesize[0] && my >y*tilesize[1] && my<(y+1)*tilesize[1]){
			return true;
		}
		return false;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//200 lines of getters and setters and such related things. nothing to see here
	public String toString(){
		return name+": "+hp ;
		
	}
	
	public boolean equals(Character c){
		return c.getName().equals(name);
	}
	
	public void addEffect(EffectTimer eff){
		effs.add(eff);
	}

	public int getHp() {
		return hp;
	}

	public void addHp(int hp){
		this.hp+=hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttack() {
		return attack;
	}
	
	public void addAttack(int attack){
		this.attack+=attack;
	}
	
	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void addDefence(int def){
		this.defence+=def;
	}
	
	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getMagicres() {
		return magicres;
	}

	public void setMagicres(int magicres) {
		this.magicres = magicres;
	}

	public int getRange() {
		return range;
	}
	
	public void addRange(int r){
		this.range+=r;
	}
	
	public void setRange(int range) {
		this.range = range;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void addSpeed(int sp){
		this.speed+=sp;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getName() {
		return name;
	}

	public void setName(String sprite) {
		this.name = sprite;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Terrain getSpace() {
		return space;
	}

	public void setSpace(Terrain space) {
		this.space = space;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public boolean hasAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}
	
	public int getCurrentHP() {
		return chp;
	}

	public void setCurrentHP(int chp) {
		this.chp = chp;
	}
	
	public ImageIcon getIcon(){
		return this.icon;
	}
	
	public Spell getSpell(int index){
		return spells[index];
	}
	
	public void addSpellShield(){
		sShieldCount++;
	}
	
	public void addPhysShield(){
		pShieldCount++;
	}

	public boolean isSilenced() {
		return silenced;
	}

	public void setSilenced(boolean silenced) {
		this.silenced = silenced;
	}

	public boolean isSnared() {
		return snared;
	}

	public void setSnared(boolean snared) {
		this.snared = snared;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
	public void setSpell(Spell [] spells){
		this.spells = spells;
	}
	
	public int getsShieldCount() {
		return sShieldCount;
	}

	public void setsShieldCount(int sShieldCount) {
		this.sShieldCount = sShieldCount;
	}

	public int getpShieldCount() {
		return pShieldCount;
	}

	public void setpShieldCount(int pShieldCount) {
		this.pShieldCount = pShieldCount;
	}

	public void addMagicRes(int amount) {
		this.magicres += amount;
		
	}

	public ArrayList<EffectTimer> getEffs() {
		// TODO Auto-generated method stub
		return effs;
	}
	
	public double getModifier() {
		return modifier;
	}

	public void setModifier(double modifier) {
		this.modifier = modifier;
	}
	
	public int getSpellCasts() {
		return spellCasts;
	}

	public void addSpellCasts() {
		this.spellCasts++;
	}
	
	public void resetSpellCasts(){
		this.spellCasts = 0;
	}

	public ImageIcon getImg() {
		// TODO Auto-generated method stub
		return img;
	}
}