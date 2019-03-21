package naval;

import java.util.ArrayList;
import java.util.List;

public class Boat {
	private BoatType boatType;
	private List<Case> localisation;
	private int life;

	public Boat(BoatType bateau) {
		setBoatType(bateau);
		// @TODO : a supprimer si non utilisé
		localisation = new ArrayList<>();
		setLife(bateau.getTaille());
	}

	public void addCaseToLocalisation(Case coord) {
		localisation.add(coord);
	}

	// ----------------------------
	// -- GETTER SETTER -----------
	// ----------------------------
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
