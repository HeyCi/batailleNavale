package naval;

import java.util.ArrayList;
import java.util.List;

public class Boat {
	private BoatType boatType;
	private Orientation orientation;
	private List<Case> localisation;
	private int life;

	public Boat(BoatType bateau, Orientation orientation) {
		setBoatType(bateau);
		// @TODO : a supprimer si non utilisé
		localisation = new ArrayList<>();
		setOrientation(orientation);
		setLife(bateau.getTaille());
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

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

}
