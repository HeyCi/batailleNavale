package naval;

public class Case {
	private Letter coordLigne;
	private Number coordColonne;
	private Boat boat;

	public Case(Letter coordLigne, Number coordColonne) {
		setCoordLigne(coordLigne);
		setCoordColonne(coordColonne);
	}

	// ----------------------------
	// -- GETTER SETTER -----------
	// ----------------------------
	public void setBoat(Boat boat) {
		this.boat = boat;
	}

	public Boat getBoat() {
		return boat;
	}

	public Letter getCoordLigne() {
		return coordLigne;
	}

	public void setCoordLigne(Letter coordLigne) {
		this.coordLigne = coordLigne;
	}

	public Number getCoordColonne() {
		return coordColonne;
	}

	public void setCoordColonne(Number coordColonne) {
		this.coordColonne = coordColonne;
	}

}
