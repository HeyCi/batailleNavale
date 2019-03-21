package naval;

public class Case {
	private Position position;
	private Boat boat;

	public Case(Position position) {
		this.position = position;
		boat = null;
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

	public Position getPosition() {
		return position;
	}
}
