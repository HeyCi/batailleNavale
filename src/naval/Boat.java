package naval;

import java.util.ArrayList;
import java.util.List;

public class Boat {
	private BoatType bateau;
	private Orientation orientation;
	private List<Case> localisation;

	public Boat(BoatType bateau) {
		this.bateau = bateau;
		localisation = new ArrayList<>();
	}

	public void setLocalisation(Case coord) {
		localisation.add(coord);
	}
}
