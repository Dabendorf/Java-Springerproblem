package springerproblem;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Springerproblem implements ActionListener {
	
	private JFrame frame1 = new JFrame("Springerproblem");
	private int a = 8, b = 10;
	private JLabel[][] feld = new JLabel[a][b];
	//private static int a = 8, b = 8;
	//private static JLabel[][] feld   = new JLabel[a][b];
    //static int position[][] = new int[a][b];
    private JPanel brett = new JPanel();
    private JTextField coords = new JTextField("x,y",8);
    
    private JLabel info = new JLabel("Startkoordinaten (von 1,1 bis "+(a)+","+(b)+"):");
    private JButton start = new JButton("Berechnen");
    
    public Springerproblem() {
    	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(350,350));
		frame1.setResizable(false);
		Container cp = frame1.getContentPane();
		cp.setLayout(new FlowLayout(FlowLayout.LEFT,50,20));
		cp.add(brett);
        cp.add(info);
        cp.add(coords);
        cp.add(start);
        brett.setLayout(new GridLayout(a,b));
        brett.setPreferredSize(new Dimension(200,200));
        info.setPreferredSize(new Dimension(350,13));
        start.addActionListener(this);
        
        for(int y=0; y<b; y++) {
        	for(int x=0; x<a; x++) {
        		feld[x][y] = new JLabel(" ",JLabel.CENTER);
                feld[x][y].setBorder(new LineBorder(Color.black,1));
                brett.add(feld[x][y]);
                feld[x][y].setOpaque(true);
                feld[x][y].setBackground(Color.lightGray);
        	}
        }
		frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);                                  
    }
    
    public void actionPerformed(ActionEvent e) {
    	for(int x=0; x<a; x++) {
        	for(int y=0; y<b; y++) {
        		feld[x][y].setText("");
        	}
        }
    	
    	Algorithmus alg = new Algorithmus(a,b);
    	int x, y;
        String s;
        s = String.valueOf(coords.getText().charAt(0));
        x = Integer.parseInt(s)-1;
        s = String.valueOf(coords.getText().charAt(2));
        y = Integer.parseInt(s)-1;
        
        alg.solve(x,y,1);
        for(int i=0; i<a; i++) {
    		for(int j=0; j<b; j++) {
    			feld[i][j].setText(String.valueOf(alg.get(i,j)));
    		}
    	}
        frame1.repaint();
    }
    
    public static void main(String[] args) {
    	new Springerproblem();
    }   
}