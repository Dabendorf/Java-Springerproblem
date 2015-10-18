package springerproblem;

import java.util.Arrays;

public class Schachbrett implements Cloneable {
	
	private Einzelfeld[][] feld = new Einzelfeld[8][8];
	private String[] spielzuege = new String[64];
	private int zaehler = 0;

	private int[] letzterZug = new int[2];

	public Schachbrett() {
		for(int a=0;a<Springerproblem.getBreite();a++) {
			for(int b=0;b<Springerproblem.getLaenge();b++) {
				feld[a][b] = new Einzelfeld(a,b);
			}
		}
	}
	
	public void spielzug(int buchstabeNum, int zahl) {
		feld[buchstabeNum][zahl].setBesetzt(true);
		spielzuege[zaehler] = feld[buchstabeNum][zahl].toString();
		letzterZug[0] = buchstabeNum;
		letzterZug[1] = zahl;
		zaehler++;
	}
	
	public int getLastPosition(boolean buchstabe) {
		if(buchstabe) {
			return letzterZug[0];
		} else {
			return letzterZug[1];
		}
	}
	
	public boolean belegt(int buchstabeNum, int zahl) {
		return feld[buchstabeNum][zahl].isBesetzt();
	}

	public int getZaehler() {
		return zaehler;
	}
	
	@Override
	public String toString() {
		return "Problemlösung [spielzuege=" + Arrays.toString(spielzuege) + "]";
	}
	
	@Override
    protected Schachbrett clone() {
        try {
            return (Schachbrett) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}