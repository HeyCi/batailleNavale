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

	public void placeBoat() {
		Boat boat = new Boat(BoatType.SousMarin, Orientation.Horizontal);
		for (int i = 0; i < BoatType.SousMarin.getTaille(); i++) {
			Case caseSuivante = caseList.get(26 + i);
			caseSuivante.setBoat(boat);
		}
	}

	public void placeBoat2(BoatType boatType, Orientation orientation) {
		Boat boat = new Boat(boatType, orientation);
		if (boat.getOrientation() == Orientation.Horizontal) {
			int indexCaseDepart = 41;
			for (int i = 0; i < boat.getBoatType().getTaille(); i++) {
				Case caseSuivante = caseList.get(indexCaseDepart + i);
				caseSuivante.setBoat(boat);
			}
		} else if (boat.getOrientation() == Orientation.Vertical) {
			int indexCaseDepart = 41;
			int coordColonneDepart = caseList.get(indexCaseDepart).getCoordColonne().getValeurDeColonne();
			int coordLigneDepart = caseList.get(indexCaseDepart).getCoordLigne().getValeurDeLigne();
			for (int i = 0; i < boat.getBoatType().getTaille(); i++) {
				for (Case currentCase : caseList) {
					if (currentCase.getCoordColonne().getValeurDeColonne() == coordColonneDepart + i
							&& currentCase.getCoordLigne().getValeurDeLigne() == coordLigneDepart) {
						currentCase.setBoat(boat);
					}
				}
			}
		}
	}

	/**
	 * Permet d'afficher la grille de jeu : X si un bateau O si case vide
	 */
	public void showGrid() {
		for (Case currentCase : caseList) {
			if (currentCase.getBoat() == null) {
				System.out.print("O");
			} else {
				System.out.print("X");
			}
			if (currentCase.getCoordLigne() == Letter.J) {
				System.out.println(" ");
			}
		}
	}
}
