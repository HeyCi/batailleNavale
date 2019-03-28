package naval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BoatGrid {
	private static final String BOAT_CASE = "X ";
	private static final String EMPTY_CASE = ". ";
	private Map<Position, Case> caseList;

	public BoatGrid() {
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

	public ArrayList<Position> generatePositionList(Boat boat, Position positionDepart, Orientation orientation) {
		ArrayList<Position> boatCaseList = new ArrayList<>();
		// vérifier si ça va dépasser de la grille :
		if (positionDepart.getValeurCoordColonne() + boat.getBoatType().getTaille() > Letter.values().length
				|| positionDepart.getValeurCoordLigne() + boat.getBoatType().getTaille() > Number.values().length) {
			return null;
		}
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

	public boolean checkIfBoatFits(ArrayList<Position> positionListToTest) {
		boolean isFree = true;
		for (Position positionToTest : positionListToTest) {
			Case caseToTest = caseList.get(positionToTest);
			if (caseToTest.getBoat() != null) {
				return false;
			}
			for (int i = -1; i < 2; i++) {
				for (int j = -1; j < 2; j++) {
					int indexColonne = positionToTest.getValeurCoordColonne() + i;
					int indexLigne = positionToTest.getValeurCoordLigne() + j;
					if (indexLigne < Letter.values().length && indexColonne < Number.values().length
							&& indexColonne >= 0 && indexLigne >= 0) {
						Position surroundingPosition = new Position(Letter.values()[indexLigne],
								Number.values()[indexColonne]);
						Case caseSurrounding = caseList.get(surroundingPosition);
						if (caseSurrounding.getBoat() != null) {
							return false;
						}
					}
				}
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
			if (generatedPositionList != null) {
				boatFits = checkIfBoatFits(generatedPositionList);
			}
		}
		setBoatInCases(boat, generatedPositionList);
	}

	public CaseStatus checkCaseStatus(Position position) {
		Case caseToCheck = caseList.get(position);
		if (caseToCheck.getBoat() == null) {
			return CaseStatus.Eau;
		} else {
			caseToCheck.getBoat().setLife(caseToCheck.getBoat().getLife() - 1);
			if (caseToCheck.getBoat().getLife() == 0) {
				return CaseStatus.Coule;
			} else {
				return CaseStatus.Touche;
			}
		}
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
