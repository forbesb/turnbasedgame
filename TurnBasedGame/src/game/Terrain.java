package game;

import java.awt.*;
import javax.swing.ImageIcon;


public abstract class Terrain {
	private int def;
	private boolean passable;
	private ImageIcon img; //instance vars
	private Color bgcolor = new Color(255,0,0), borderColor = new Color(0,0,0);
	private boolean lit = false;
	private int x, y;
	
	
	public Terrain(int x2, int y2) {
		x= x2;
		y =y2;
	}

	public abstract void terrEffect(Character c); //all terrain have an effect
	 
	//getters and setters
	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public ImageIcon getImg() {
		return img;
	}

	public void setImg(ImageIcon img) {
		this.img = img;
	}

	public Color getBgcolor() {
		return bgcolor;
	}

	public void setBgcolor(Color bgcolor) {
		this.bgcolor = bgcolor;
	}

	public String toString(){
		//mostly unused
		if ((def+"").length()<2) return " "+def;
		else return def+ "";
	}
	
	public void drawSelf(Graphics g, int x, int y, int[] tilesize){
		g.setColor(new Color(0,0,0)); //draws self on the given coords
		g.drawRect(x-1, y-1, tilesize[0]+1, tilesize[1]+1);
		if (img==null){
			g.setColor(bgcolor);
			g.fillRect(x, y, tilesize[0], tilesize[1]);
		} else {
			g.drawImage(img.getImage(), x, y, tilesize[0], tilesize[1], null);
		}
		
		if (lit){
			g.setColor(borderColor);
			g.drawRect(x+1, y+1, tilesize[0]-4, tilesize[1]-4);
		}
	}

	public boolean intersects(int mx, int my, int[] tilesize){
		if (mx >x*tilesize[0] && mx < (x+1)*tilesize[0] && my >y*tilesize[1] && my<(y+1)*tilesize[1]){
			return true; //simple intersect
		}
		return false;
		
	}
	 //more getters and setters
	
	public boolean isPassable() {
		return passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}
	
	public void light(Color c){
		lit = true;
		borderColor = c;
	}
	
	public void dark(){
		lit = false;
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
}
