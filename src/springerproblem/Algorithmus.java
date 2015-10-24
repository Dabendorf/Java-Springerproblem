package springerproblem;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Dieser Algorithmus versucht sich daran das Springerproblem auf einem x*y grossen Schachbrett zu loesen.
 * Algorithmus abgewandelt von http://forum.byte-welt.net/java-forum-das-java-welt-kompetenz-zentrum-wir-wissen-und-helfen-/allgemeine-themen/17566-clonen-funktioniert-nicht.html#post124909
 * 
 * @author Marco13
 * @author Lukas Schramm
 * @version 1.0
 *
 */
public class Algorithmus {
	
	/**Groesse des Feldes in sizeX*sizeY*/
	private final int sizeX, sizeY;
	/**Das Spielfeld aus Feldern*/
    private final int board[];
    /**Dies ist die Liste an Zuegen, die ein Springer auf einem Schachbrett ausfuehren kann*/
    private final Point deltas[] = {
        new Point(-1,  2),
        new Point( 1,  2),
        new Point(-1, -2),
        new Point( 1, -2),
        new Point(-2,  1),
        new Point( 2,  1),
        new Point(-2, -1),
        new Point( 2, -1),
    };
   
    public Algorithmus(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = new int[sizeX*sizeY];
    }
   
    /**
     * Diese Methode gibt die Zugnummer aus, mit welcher das Feld beruehrt wird.
     * @param x Die x-Koordinate des Felds.
     * @param y Die y-Koordinate des Felds.
     * @return Gibt die Zugnummer zurueck.
     */
    public int get(int x, int y) {
        return board[x+y*sizeX];
    }
    
    /**
     * Diese Methode setzt die Zugnummer fest, mit welcher das Feld beruehrt wird.
     * @param x Die x-Koordinate des Felds.
     * @param y Die y-Koordinate des Felds.
     * @param n Legt die Zugnummer fest.
     */
    private void set(int x, int y, int n) {
        board[x+y*sizeX] = n;
    }
    
    /**
     * Diese Methode ueberprueft, ob das Feld gueltig ist.
     * @param x Die x-Koordinate des Felds.
     * @param y Die y-Koordinate des Felds.
     * @return Gibt einen Boolean zurueck.
     */
    private boolean isValid(int x, int y) {
        return x >= 0 && x < sizeX && y >= 0 && y < sizeY;
    }
    
    /**
     * Diese Methode generiert ausgehend von einem Feld die acht anderen Felder, den der Springer besuchen koennte.
     * @param x Die x-Koordinate des Felds.
     * @param y Die y-Koordinate des Felds.
     * @return Gibt eine Liste an Punkten zurueck.
     */
    private List<Point> computeTargets(int x, int y) {
        List<Point> targets = new ArrayList<Point>();
        for(Point delta : deltas) {
            int tx = x + delta.x;
            int ty = y + delta.y;
            if(isValid(tx, ty) && get(tx, ty) == 0) {
                targets.add(new Point(tx, ty));
            }
        }
        return targets;
    }
    
    /**
     * Diese Methode fuehrt jeden einzelnen Schritt aus und gibt zurueck, ob das Brett komplett geloest ist.
     * @param currentX Die x-Koordinate des Felds.
     * @param currentY Die y-Koordinate des Felds.
     * @param step Die Nummer des Spielzugs.
     * @return Gibt Boolean zurueck, ob das Springerproblem geloest wurde.
     */
    public boolean solve(int currentX, int currentY, int step) {
        if(step == sizeX * sizeY) {
            set(currentX, currentY, step);
            return true;
        }
        set(currentX, currentY, step);
       
        List<Point> targets = computeTargets(currentX, currentY);
       
        applyWarnsdorffRule(targets);
       
        for(Point target : targets) {
            boolean solved = solve(target.x, target.y, step+1);
            if(solved) {
                return true;
            }
        }
        set(currentX, currentY, 0);
        return false;
    }
   
    /**
     * Diese Methode fuehrt die Warnsdorffregel aus, nach welcher das Feld besucht werden sollte,
     * von welchem man die wenigsten anderen Felder besuchen kann.
     * @param targets Die Liste an Punkten.
     */
    private void applyWarnsdorffRule(List<Point> targets) {
        Map<Point, Integer> targetCounters = new HashMap<Point, Integer>();
        for(Point target : targets) {
            List<Point> nextTargets = computeTargets(target.x, target.y);
            targetCounters.put(target, nextTargets.size());
        }
        Collections.sort(targets, new Comparator<Point>() {
            @Override
            public int compare(Point p0, Point p1) {
                Integer c0 = targetCounters.get(p0);
                Integer c1 = targetCounters.get(p1);
                return Integer.compare(c0, c1);
            }
        });
    }
}