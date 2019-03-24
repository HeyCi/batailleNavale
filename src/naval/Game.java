package naval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game {
	private static final String BOAT_CASE = "X ";
	private static final String EMPTY_CASE = ". ";
	private Map<Position, Case> caseList;

	public Game() {
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
		System.out.println(randomColonne + "" + randomLigne);
		return randomPosition;
	}

	public Orientation generateRandomOrientation() {
		Orientation orientation = Orientation.values()[(int) Math.floor((Math.random() * Orientation.values().length))];
		return orientation;
	}

	public ArrayList<Position> generatePositionList(Boat boat, Position positionDepart, Orientation orientation) {
		ArrayList<Position> boatCaseList = new ArrayList<>();
		for (int i = 0; i < boat.getBoatType().getTaille(); i++) {
			if (orientation == Orientation.Vertical) {
				Letter ligne = Letter.values()[(positionDepart.getValeurCoordLigne()) + i];
				Number colonne = positionDepart.getNomCoordColonne();
				Position position = new Position(ligne, colonne);
				boatCaseList.add(position);
			} else if (orientation == Orientation.Horizontal) {
				Letter ligne = positionDepart.getNomCoordLigne();
				Number colonne = Number.values()[(positionDepart.getValeurCoordLigne()) + i];
				Position position = new Position(ligne, colonne);
				boatCaseList.add(position);
			}
		}
		return boatCaseList;
	}

	public boolean checkIfBoatFits(ArrayList<Position> positionListToTest) { // TODO comment tester si ça depasse de la
																				// grille ????
		boolean isFree = true;
		for (Position positionToTest : positionListToTest) {
			Case caseToTest = caseList.get(positionToTest);
			if (caseToTest.getBoat() != null) {
				isFree = false;
			}
		}
		return isFree;
	}

	public void setBoatInCases(Boat boat, ArrayList<Position> positionToPlaceList) {
		for (Position position : positionToPlaceList) {
			Case caseToModify = caseList.get(position);
			caseToModify.setBoat(boat);
		}
	}

	public void placeBoat(BoatType boatType) {
		Boat boat = new Boat(boatType);
		Boolean boatFits = false;
		ArrayList<Position> generatedPositionList = new ArrayList<>();
		while (!boatFits) {
			Orientation randomOrientation = generateRandomOrientation();
			Position randomPositionDepart = getRandomPosition();
			generatedPositionList = generatePositionList(boat, randomPositionDepart, randomOrientation);
			boatFits = checkIfBoatFits(generatedPositionList);
		}
		setBoatInCases(boat, generatedPositionList);
	}

	public void showGrid() {
		caseList.forEach((position, currentCase) -> {
			if (currentCase.getBoat() == null) {
				System.out.print(EMPTY_CASE);
			} else {
				System.out.print(BOAT_CASE);
			}
			if (currentCase.getPosition().getNomCoordColonne() == Number.DIX) {
				System.out.println(" ");
			}
		});
	}
}
