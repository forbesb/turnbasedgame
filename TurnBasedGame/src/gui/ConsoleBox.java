package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConsoleBox extends JPanel {
	private JTextArea text = new JTextArea();
	
	public static void main(String[] args){
		//testing purposes
		JFrame root = new JFrame();
		root.setVisible(true);
		root.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        root.setSize (300, 300);
        
		ConsoleBox c = new ConsoleBox(300,300);
		c.setSize(root.getSize());
		root.add(c);
		
        root.repaint();
		
	}
	public ConsoleBox(int l, int w) {
		this.setLayout(new BorderLayout());
        
		text.setEditable(false); //set up text box
		text.setText("GAME LOG\n");
		Container c = new Container();
		c.setSize(l,w); //set up container
		JScrollPane spane = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spane.setSize(c.getWidth()-15,c.getHeight()-35); //set up scrollpane with text box
		//System.out.println(spane.getWidth()+":"+spane.getHeight());
		spane.setVisible(true);
		c.add(spane);
		
		c.setVisible(true);
		this.add(c, BorderLayout.CENTER);
		
		System.out.println("gg");
	}
	
	public void println(String ln){
		String prevl = text.getText(); //change text to add the new line
		text.setText(prevl + ln + "\n");
	}

}
