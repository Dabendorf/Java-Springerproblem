package springerproblem;

public class Einzelfeld {
	
	private boolean besetzt = false;
	private String[] buchstaben = {"A", "B", "C", "D", "E", "F", "G", "H"};
	private int[] koordinaten = new int[2];
	private String bezeichnung;
	
	public Einzelfeld(int buchstabeNum, int zahl) {
		koordinaten[0] = buchstabeNum;
		koordinaten[1] = zahl;
		bezeichnung = buchstaben[buchstabeNum]+(zahl+1);
	}

	public boolean isBesetzt() {
		return besetzt;
	}

	public void setBesetzt(boolean besetzt) {
		this.besetzt = besetzt;
	}
	
	public int[] getKoordinaten() {
		return koordinaten;
	}

	@Override
	public String toString() {
		return "["+bezeichnung+"]";
	}
}