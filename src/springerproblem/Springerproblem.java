package springerproblem;

import java.util.ArrayList;

public class Springerproblem {
	
	private static int breite=8, laenge=8;
	private ArrayList<Schachbrett> geschlossen = new ArrayList<Schachbrett>();
	private ArrayList<Schachbrett> offen = new ArrayList<Schachbrett>();
	private int a = 0;

	public Springerproblem() {
		for(int a=0;a<breite;a++) {
			for(int b=0;b<laenge;b++) {
				Schachbrett sb = new Schachbrett();
				sb.spielzug(a,b);
				naechsteReihe(sb);
			}
		}
		System.out.println(a);
	}
	
	private void naechsteReihe(Schachbrett sb) {
		a++;
		System.out.println("Runde: "+sb.getZaehler()+" ");
		if(sb.getLastPosition(true)-2>=0 && sb.getLastPosition(false)-1>=0) {
			if(!sb.belegt(sb.getLastPosition(true)-2, sb.getLastPosition(false)-1)) {
				Schachbrett sb1 = sb.clone();
				if(sb1.getZaehler()<breite*laenge) {
					sb1.spielzug(sb1.getLastPosition(true)-2,sb1.getLastPosition(false)-1);
					naechsteReihe(sb1);
				} else if(sb1.getZaehler()==breite*laenge) {
					System.out.println(sb1.toString());
				}
			}
		}
		if(sb.getLastPosition(true)-2>=0 && sb.getLastPosition(false)+1<laenge) {
			if(!sb.belegt(sb.getLastPosition(true)-2, sb.getLastPosition(false)+1)) {
				Schachbrett sb1 = sb.clone();
				if(sb1.getZaehler()<breite*laenge) {
					sb1.spielzug(sb1.getLastPosition(true)-2,sb1.getLastPosition(false)+1);
					naechsteReihe(sb1);
				} else if(sb1.getZaehler()==breite*laenge) {
					System.out.println(sb1.toString());
				}
			}
		}
		if(sb.getLastPosition(true)+2<breite && sb.getLastPosition(false)-1>=0) {
			if(!sb.belegt(sb.getLastPosition(true)+2, sb.getLastPosition(false)-1)) {
				Schachbrett sb1 = sb.clone();
				if(sb1.getZaehler()<breite*laenge) {
					sb1.spielzug(sb1.getLastPosition(true)+2,sb1.getLastPosition(false)-1);
					naechsteReihe(sb1);
				} else if(sb1.getZaehler()==breite*laenge) {
					System.out.println(sb1.toString());
				}
			}
		}
		if(sb.getLastPosition(true)+2<breite && sb.getLastPosition(false)+1<laenge) {
			if(!sb.belegt(sb.getLastPosition(true)+2, sb.getLastPosition(false)+1)) {
				Schachbrett sb1 = sb.clone();
				if(sb1.getZaehler()<breite*laenge) {
					sb1.spielzug(sb1.getLastPosition(true)+2,sb1.getLastPosition(false)+1);
					naechsteReihe(sb1);
				} else if(sb1.getZaehler()==breite*laenge) {
					System.out.println(sb1.toString());
				}
			}
		}
		if(sb.getLastPosition(true)-1>=0 && sb.getLastPosition(false)-2>=0) {
			if(!sb.belegt(sb.getLastPosition(true)-1, sb.getLastPosition(false)-2)) {
				Schachbrett sb1 = sb.clone();
				if(sb1.getZaehler()<breite*laenge) {
					sb1.spielzug(sb1.getLastPosition(true)-1,sb1.getLastPosition(false)-2);
					naechsteReihe(sb1);
				} else if(sb1.getZaehler()==breite*laenge) {
					System.out.println(sb1.toString());
				}
			}
		}
		if(sb.getLastPosition(true)-1>=0 && sb.getLastPosition(false)+2<laenge) {
			if(!sb.belegt(sb.getLastPosition(true)-1, sb.getLastPosition(false)+2)) {
				Schachbrett sb1 = sb.clone();
				if(sb1.getZaehler()<breite*laenge) {
					sb1.spielzug(sb1.getLastPosition(true)-1,sb1.getLastPosition(false)+2);
					naechsteReihe(sb1);
				} else if(sb1.getZaehler()==breite*laenge) {
					System.out.println(sb1.toString());
				}
			}
		}
		if(sb.getLastPosition(true)+1<breite && sb.getLastPosition(false)-2>=0) {
			if(!sb.belegt(sb.getLastPosition(true)+1, sb.getLastPosition(false)-2)) {
				Schachbrett sb1 = sb.clone();
				if(sb1.getZaehler()<breite*laenge) {
					sb1.spielzug(sb1.getLastPosition(true)+1,sb1.getLastPosition(false)-2);
					naechsteReihe(sb1);
				} else if(sb1.getZaehler()==breite*laenge) {
					System.out.println(sb1.toString());
				}
			}
		}
		if(sb.getLastPosition(true)+1<breite && sb.getLastPosition(false)+2<laenge) {
			if(!sb.belegt(sb.getLastPosition(true)+1, sb.getLastPosition(false)+2)) {
				Schachbrett sb1 = sb.clone();
				if(sb1.getZaehler()<breite*laenge) {
					sb1.spielzug(sb1.getLastPosition(true)+1,sb1.getLastPosition(false)+2);
					naechsteReihe(sb1);
				} else if(sb1.getZaehler()==breite*laenge) {
					System.out.println(sb1.toString());
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Springerproblem();
	}

	public static int getBreite() {
		return breite;
	}

	public static int getLaenge() {
		return laenge;
	}
}