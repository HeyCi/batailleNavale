package naval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class game {
	private Map<Position, Case> caseList;

	public game() {
		caseList = new HashMap<>();
		for (Number number : Number.values()) {
			for (Letter letter : Letter.values()) {
				Position position = new Position(letter, number);
				Case cell = new Case(position);
				caseList.put(position, cell);
			}
		}
	}

	public Position getRandomPosition() {
		Letter randomLigne = Letter.values()[(int) Math.floor((Math.random() * Letter.values().length))];
		Number randomColonne = Number.values()[(int) Math.floor((Math.random() * Number.values().length))];
		Position randomPosition = new Position(randomLigne, randomColonne);
		return randomPosition;
	}

	public Orientation generateRandomOrientation() {
		Orientation orientation = Orientation.values()[(int) Math.floor((Math.random() * Orientation.values().length))];
		return orientation;
	}

	public ArrayList<Case> generateCaseList(Boat boat, Position positionDepart, Orientation orientation) {
		ArrayList<Case> boatCaseList = new ArrayList<>();
		for (int i = 0; i < boat.getBoatType().getTaille(); i++) {
			// ici un if pour gérer horizontal/vertical
			Position position = null; // = position + i
			Case caseToAdd = caseList.get(position);
			boatCaseList.add(caseToAdd);
		}
		return boatCaseList;
	}

	public boolean checkIfBoatFits(ArrayList<Case> CaseListToTest) {
		// TODO implémenter,liste de case en entrée, retourner un booléen
		return false;
	}

	public void setBoatInCases() {
		// TODO implémenter, avec liste de case en entrée
	}

	public void placeBoat(BoatType boatType) {
		Boat boat = new Boat(boatType);
		Boolean boatFits = false;
		while (!boatFits) {
			Orientation randomOrientation = generateRandomOrientation();
			Position randomPositionDepart = getRandomPosition();
			ArrayList<Case> testCaseList = generateCaseList(boat, randomPositionDepart, randomOrientation);
			boatFits = checkIfBoatFits(testCaseList);
		}
		setBoatInCases();
	}
}
