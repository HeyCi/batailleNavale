package naval;

public class Case {
	private Position position;
	private Boat boat;
	private CaseStatus caseStatus;

	public Case(Position position) {
		this.position = position;
		boat = null;
		setCaseStatus(CaseStatus.Libre);
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

	public CaseStatus getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(CaseStatus caseStatus) {
		this.caseStatus = caseStatus;
	}
}
