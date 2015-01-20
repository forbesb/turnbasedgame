package client.mainClient.shop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


import client.mainClient.shop.Item;

@SuppressWarnings("serial")
public class ItemPanel extends JPanel implements ActionListener{
	public static boolean itemEntered = false;
	public static int xcoor = 0, ycoor = 0;
	public static String [][] data = new String[0][0];
	public static int total = 0;
	public static ArrayList<Item> boughtItems = new ArrayList<Item>();
	public Timer t;
	private static Item [] items = {
		new Item(100,new String [] {"Item1","Random Description","abcde"},null, new ImageIcon("Item1.jpg")),
		new Item(100,new String [] {"Item2","Random Description","abcde"},null, new ImageIcon("Item2.jpg")),
		new Item(100,new String [] {"Item3","Random Description","abcde"},null, new ImageIcon("Item3.jpg")),
		new Item(100,new String [] {"Item4","Random Description","abcde"},null, new ImageIcon("Item4.jpg")),
		new Item(100,new String [] {"Item5","Random Description","abcde"},null, new ImageIcon("Item5.jpg")),
		new Item(100,new String [] {"Item6","Random Description","abcde"},null, new ImageIcon("Item6.jpg")),
		new Item(100,new String [] {"Item7","Random Description","abcde"},null, new ImageIcon("Item7.jpg")),
		new Item(100,new String [] {"Item8","Random Description","abcde"},null, new ImageIcon("Item8.jpg")),
		new Item(100,new String [] {"Item9","Random Description","abcde"},null, new ImageIcon("Item9.jpg")),
		new Item(100,new String [] {"Item10","Random Description","abcde"},null, new ImageIcon("Item10.jpg")),
		new Item(100,new String [] {"Item11","Random Description","abcde"},null, new ImageIcon("Item11.jpg")),
		new Item(100,new String [] {"Item12","Random Description","abcde"},null, new ImageIcon("Item12.jpg"))
	};
	private static JCheckBox[] checkBoxes = new JCheckBox[12];
	private ItemShelf [] itemComboes = new ItemShelf[12];
	private GridBagConstraints gbc = new GridBagConstraints();
	private ArrayList<Integer> used = new ArrayList<Integer>();
	public ItemPanel(){
		t = new Timer(100, this);
		t.start();
		this.setBackground(new Color(255,255,255));
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0,10,0,0);
		this.setLayout(new GridBagLayout());
		int k = 0;
		for(int i = 0; i < 3; i ++){
			for (int j = 0; j<4; j++){
				checkBoxes[k] = new JCheckBox("Purchase");
				checkBoxes[k].setBackground(new Color(255,255,255));
				itemComboes[k] = new ItemShelf();
				itemComboes[k].setValue(items[k].getValue());
				itemComboes[k].setMessage(items[k].getDescription());
				itemComboes[k].setLayout(new BorderLayout());
				itemComboes[k].add(new JLabel(items[k].getImage()), BorderLayout.CENTER);
				itemComboes[k].add(checkBoxes[k],BorderLayout.SOUTH);
				itemComboes[k].setPreferredSize(new Dimension(85,75));
				itemComboes[k].setBackground(new Color(255,255,255));
				gbc.gridx = j;
				gbc.gridy = i;
				this.add(itemComboes[k],gbc);
				k++;
			}
		}
		
	}
	public static Item [] getItems(){
		return items;
	}
	public static void reset() {
		for (JCheckBox i : checkBoxes){		
			i.setSelected(false);
		}
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if (itemEntered){
			int i = 0;
			for (i = 0; i < data[0].length; i ++){
				if (i == 0){
					g.setColor(new Color(0,0,0));
					g.setFont(new Font("Calibri",Font.BOLD,14));
				}else if (i == 1){
					g.setColor(new Color(0,0,0));
					g.setFont(new Font("Calibri",Font.BOLD,10));
				}
				g.drawChars(data[0][i].toCharArray(), 0, data[0][i].length(), xcoor, ycoor+i*10);
				
			}
			g.setColor(new Color(218,165,32));
			g.drawChars(data[1][0].toCharArray(),0,data[1][0].length(),xcoor, ycoor+(i)*10);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < 12; i ++){
			if (checkBoxes[i].isSelected() && used.contains(i) == false){
				boughtItems.add(items[i]);
				total += items[i].getValue();
				used.add(i);
			}else if (used.contains(i) == true && checkBoxes[i].isSelected() == false){
				used.remove((Integer)i);
				total -= items[i].getValue();
			}
		}
		repaint();
		
	}
}