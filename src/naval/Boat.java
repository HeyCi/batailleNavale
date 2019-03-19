package naval;

import java.util.ArrayList;
import java.util.List;

public class Boat {
	private BoatType boatType;
	private Orientation orientation;
	private List<Case> localisation;

	public Boat(BoatType bateau, Orientation orientation) {
		setBoatType(bateau);
		localisation = new ArrayList<>();
		setOrientation(orientation);
	}

	public void addCaseToLocalisation(Case coord) {
		localisation.add(coord);
	}

	// ----------------------------
	// -- GETTER SETTER -----------
	// ----------------------------
	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public BoatType getBoatType() {
		return boatType;
	}

	public void setBoatType(BoatType bateau) {
		boatType = bateau;
	}

}
