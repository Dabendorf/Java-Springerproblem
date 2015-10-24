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

/**
 * http://forum.byte-welt.net/java-forum-das-java-welt-kompetenz-zentrum-wir-wissen-und-helfen-/allgemeine-themen/17566-clonen-funktioniert-nicht.html#post124881
 * https://www.hackerboard.de/programmieraufgaben/16975-springerproblem.html
 * http://www.axel-conrad.de/springer/springer.html
 * https://de.wikipedia.org/wiki/Springerproblem#Warnsdorffregel
 * http://www.zaik.uni-koeln.de/AFS/teachings/ss07/InfoSeminar/handout/sascha_baumanns_knighttours.pdf
 * @author lukasschramm
 *
 */
public class AASpringerproblemAlt implements ActionListener {
	
	private JFrame frame1 = new JFrame("Springerproblem");
	private static int a = 5;
	private static JLabel[][] feld   = new JLabel[a][a];
    static int position[][] = new int[a][a];
    private JPanel brett = new JPanel();
    private JTextField coords = new JTextField("x,y",8);
    private JLabel info = new JLabel("Starkoordinaten (von 0,0 bis "+(a-1)+","+(a-1)+"):");
    private JButton start = new JButton("Berechnen");
    
    public AASpringerproblemAlt() {
    	frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(350,350));
		frame1.setResizable(false);
		Container cp = frame1.getContentPane();
		cp.setLayout(new FlowLayout(FlowLayout.LEFT,50,20));
		cp.add(brett);
        cp.add(info);
        cp.add(coords);
        cp.add(start);
        brett.setLayout(new GridLayout(a,a));
        brett.setPreferredSize(new Dimension(200,200));
        info.setPreferredSize(new Dimension(350,13));
        start.addActionListener(this);
        
        for(int y=0; y<a; y++) {
        	for(int x=0; x<a; x++) {
        		feld[x][y] = new JLabel(" ",JLabel.CENTER);
                feld[x][y].setBorder(new LineBorder(Color.black,1));
                brett.add(feld[x][y]);
                feld[x][y].setOpaque(true);
                feld[x][y].setBackground(Color.lightGray);
                position[x][y] = 0;
        	}
        }
		frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);                                  
    }
    
    public void actionPerformed(ActionEvent e) {
        for(int x=0; x<a; x++) {
        	for(int y=0; y<a; y++) {
        		feld[x][y].setText("");
        	}
        }
        for(int i=0; i<a*a; i++) {
            AAAlgorithmus.xWeg[i]=0;
            AAAlgorithmus.yWeg[i]=0;           
        }
        AAAlgorithmus.first = true;
        int x, y;
        String s;
        s = String.valueOf(coords.getText().charAt(0));
        x = Integer.parseInt(s);
        s = String.valueOf(coords.getText().charAt(2));
        y = Integer.parseInt(s);

        if(AAAlgorithmus.solve(x,y,1)) {
        	for(int i=0; i<a*a; i++) {
        		feld[AAAlgorithmus.xWeg[i]][AAAlgorithmus.yWeg[i]].setText(""+i);
        	}
        	for(int i=0; i<a; i++) {
        		for(int j=0; j<a; j++) {
        			if(feld[i][j].getText().equals("")) {
        				feld[i][j].setText(String.valueOf(a*a));
        			}
                    if(feld[i][j].getText().equals("1")||feld[i][j].getText().equals(String.valueOf(a*a))) {
                    	feld[i][j].setForeground(Color.blue);
                    }  
        		}
        	}         
        } else {
        	coords.setText("Keine Lösung");
        }
        for(int i=0; i<a; i++) {
        	for(int j=0; j<a; j++) {
        		position[i][j]=0;
        	}     
        }    
    }
    
    public static void main(String[] args) {
    	new AASpringerproblemAlt();
    }   
}