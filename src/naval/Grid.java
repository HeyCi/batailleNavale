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

	public void placeBoat(BoatType boatType, Orientation orientation) {
		Boat boat = new Boat(boatType, orientation);
		int numberOfOccupiedCases = 0;

		if (boat.getOrientation() == Orientation.Horizontal) {
			int indexCaseDepart = (int) Math.floor((Math.random() * caseList.size()));
			int coordColonneDepart = caseList.get(indexCaseDepart).getCoordColonne().getValeurDeColonne();
			int coordLigneDepart = caseList.get(indexCaseDepart).getCoordLigne().getValeurDeLigne();
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
				placeBoat(boatType, orientation);
			}
		} else if (boat.getOrientation() == Orientation.Vertical) {
			int indexCaseDepart = (int) Math.floor((Math.random() * caseList.size()));
			int coordColonneDepart = caseList.get(indexCaseDepart).getCoordColonne().getValeurDeColonne();
			int coordLigneDepart = caseList.get(indexCaseDepart).getCoordLigne().getValeurDeLigne();
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
				placeBoat(boatType, orientation);
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
