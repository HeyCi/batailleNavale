package naval;

public class BattleTest {

	public static void main(String[] args) {
		BoatGrid boatGrid = new BoatGrid();
		for (BoatType boatType : BoatType.values()) {
			boatGrid.placeBoat(boatType);
		}
		boatGrid.showGrid();

		ShotGrid shotGrid = new ShotGrid();
//		for (Position position : shotGrid.getTargetList()) {
//			System.out.println(position.getNomCoordColonne() + " : " + position.getNomCoordLigne());
//		}
		while (shotGrid.getNbCoule() < 5) {
			Position position = shotGrid.shoot();
			CaseStatus caseStatus = boatGrid.checkCaseStatus(position);
			shotGrid.updateCase(position, caseStatus);
		}
		System.out.println(" ");
		shotGrid.showGrid();
	}

}
