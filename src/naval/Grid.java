package naval;

import java.util.ArrayList;
import java.util.List;

public class Grid {
	private List<Case> caseList;

	public Grid() {
		caseList = new ArrayList<>();
		for (Number number : Number.values()) {
			for (Letter letter : Letter.values()) {
				Case caseNavale = new Case(letter, number);
				caseList.add(caseNavale);
			}
		}
	}

	public void placeBoat(BoatType boatType) {
		Orientation orientation = Orientation.values()[(int) Math.floor((Math.random() * Orientation.values().length))]; // orientation
																															// aléatoire
		Boat boat = new Boat(boatType);
		int numberOfOccupiedCases = 0;
		int indexCaseDepart = (int) Math.floor((Math.random() * caseList.size())); // case de départ aléatoire
		int coordColonneDepart = caseList.get(indexCaseDepart).getCoordColonne().getValeurDeColonne();
		int coordLigneDepart = caseList.get(indexCaseDepart).getCoordLigne().getValeurDeLigne();

		if (orientation == Orientation.Horizontal) {
			for (int i = 0; i < boat.getBoatType().getTaille(); i++) {
				for (Case currentCase : caseList) {
					if (currentCase.getCoordColonne().getValeurDeColonne() == coordColonneDepart
							&& currentCase.getCoordLigne().getValeurDeLigne() == coordLigneDepart + i) {
						currentCase.setBoat(boat);
						numberOfOccupiedCases++;
					}
				}
			}
			if (numberOfOccupiedCases < boat.getBoatType().getTaille()) {
				for (int i = 0; i < boat.getBoatType().getTaille(); i++) {
					for (Case currentCase : caseList) {
						if (currentCase.getCoordColonne().getValeurDeColonne() == coordColonneDepart
								&& currentCase.getCoordLigne().getValeurDeLigne() == coordLigneDepart + i) {
							currentCase.setBoat(null);
							numberOfOccupiedCases--;
						}
					}
				}
				placeBoat(boatType);
			}
		} else if (orientation == Orientation.Vertical) {
			for (int i = 0; i < boat.getBoatType().getTaille(); i++) {
				for (Case currentCase : caseList) {
					if (currentCase.getCoordColonne().getValeurDeColonne() == coordColonneDepart + i
							&& currentCase.getCoordLigne().getValeurDeLigne() == coordLigneDepart) {
						currentCase.setBoat(boat);
						numberOfOccupiedCases++;
					}
				}
			}
			if (numberOfOccupiedCases < boat.getBoatType().getTaille()) {
				for (int i = 0; i < boat.getBoatType().getTaille(); i++) {
					for (Case currentCase : caseList) {
						if (currentCase.getCoordColonne().getValeurDeColonne() == coordColonneDepart + i
								&& currentCase.getCoordLigne().getValeurDeLigne() == coordLigneDepart) {
							currentCase.setBoat(null);
							numberOfOccupiedCases--;
						}
					}
				}
				placeBoat(boatType);
			}
		}
	}

	/**
	 * Permet d'afficher la grille de jeu : X si un bateau O si case vide
	 */
	public void showGrid() {
		for (Case currentCase : caseList) {
			if (currentCase.getBoat() == null) {
				System.out.print("O ");
			} else {
				System.out.print("X ");
			}
			if (currentCase.getCoordLigne() == Letter.J) {
				System.out.println(" ");
			}
		}
	}
}
