package naval;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	private static final String BOAT_CASE = "X ";
	private static final String EMPTY_CASE = ". ";
	private List<Case> caseList;
	public String phraseTest = "";

	public Grid() {
		caseList = new ArrayList<>();
		for (Number number : Number.values()) {
			for (Letter letter : Letter.values()) {
				Case caseNavale = new Case(letter, number);
				caseList.add(caseNavale);
			}
		}
	}

	/**
	 * mon truc
	 *
	 * @param boatType hhhhh
	 */
	public void placeBoat(BoatType boatType) {
		// Determiner une position au hasard

		// vérifier que ce placement est correct

		// Mettre à jour les informations de classe pour que le bateau soit placé

		phraseTest += "nouveau bateau, ";
		Boat boat = new Boat(boatType);
		int numberOfOccupiedCases = 0;
		// orientation aléatoire :
		Orientation orientation = generateRandomOrientation();
		// case de départ aléatoire :
		int indexCaseDepart = (int) Math.floor((Math.random() * caseList.size()));
		int coordColonneDepart = caseList.get(indexCaseDepart).getCoordColonne().getValeurDeColonne();
		int coordLigneDepart = caseList.get(indexCaseDepart).getCoordLigne().getValeurDeLigne();
		List<Case> boatCaseList = new ArrayList<>();

		if (orientation == Orientation.Horizontal) {
			// que fait ceci
			for (int i = 0; i < boat.getBoatType().getTaille(); i++) {
				// que fait ceci
				for (Case currentCase : caseList) {
					// que fait ce test
					if (currentCase.getNomCoordColonne().getValeurDeColonne() == coordColonneDepart
							&& currentCase.getCoordLigne().getValeurDeLigne() == coordLigneDepart + i) {
						// que fait ce test
						if (currentCase.getBoat() == null) {
							// Dans quel cas sommes nous quand nous sommes ici ???
							currentCase.setBoat(boat);
							boatCaseList.add(currentCase);
							numberOfOccupiedCases++;
							phraseTest += "setCase h, ";
						} else {
							// Et quel est le cas ici ???
							phraseTest += "ça touche h, ";
							cancelBoatPlacementandRetry(boat, boatCaseList);
						}
					}
				}
			}
			if (numberOfOccupiedCases < boat.getBoatType().getTaille()) {
				phraseTest += "ça dépasse, ";
				cancelBoatPlacementandRetry(boat, boatCaseList);
			}
		} else if (orientation == Orientation.Vertical) {
			for (int i = 0; i < boat.getBoatType().getTaille(); i++) {
				for (Case currentCase : caseList) {
					if (currentCase.getCoordColonne().getValeurDeColonne() == coordColonneDepart + i
							&& currentCase.getCoordLigne().getValeurDeLigne() == coordLigneDepart) {
						if (currentCase.getBoat() == null) {
							currentCase.setBoat(boat);
							boatCaseList.add(currentCase);
							numberOfOccupiedCases++;
							phraseTest += "setCase v, ";
						} else {
							phraseTest += "ça touche v, ";
							cancelBoatPlacementandRetry(boat, boatCaseList);
						}
					}
				}
			}
			if (numberOfOccupiedCases < boat.getBoatType().getTaille()) {
				phraseTest += "ça dépasse, ";
				cancelBoatPlacementandRetry(boat, boatCaseList);
			}
		}
	}

	private final Orientation generateRandomOrientation() {
		Orientation orientation = Orientation.values()[(int) Math.floor((Math.random() * Orientation.values().length))];
		return orientation;
	}

	public void cancelBoatPlacementandRetry(Boat boat, List<Case> caseToDeleteList) {
		for (Case caseToDelete : caseToDeleteList) {
			caseToDelete.setBoat(null);
			phraseTest += "deleteCase, ";
		}
		placeBoat(boat.getBoatType()); // on redonne des valeurs aleatoire et on ressaye
	}

	/**
	 * Permet d'afficher la grille de jeu : X si un bateau O si case vide
	 */
	public void showGrid() {
		for (Case currentCase : caseList) {
			if (currentCase.getBoat() == null) {
				System.out.print(EMPTY_CASE);
			} else {
				System.out.print(BOAT_CASE);
			}
			if (currentCase.getCoordLigne() == Letter.J) {
				System.out.println(" ");
			}
		}
	}
}
