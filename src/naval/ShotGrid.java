package naval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShotGrid {
	private static final String BOAT_CASE = "X ";
	private static final String EMPTY_CASE = ". ";
	private static final String WATER_CASE = "O ";

	private Map<Position, Case> caseList;
	private List<Position> targetList;
	private List<Position> surroundingToucheList;
	private Position positionFirstTouche;
	private Orientation orientationTouche;
	private Position lastTarget;
	private int nbCoule;
	private ShooterState state;

	public ShotGrid() {
		setNbCoule(0);
		caseList = new HashMap<>();
		for (Number number : Number.values()) {
			for (Letter letter : Letter.values()) {
				Position position = new Position(letter, number);
				Case cell = new Case(position);
				caseList.put(position, cell);
			}
		}
		targetList = new LinkedList<>();
		for (int i = 0; i < Number.values().length; i = i + 2) {
			for (int j = 0; j < Letter.values().length; j = j + 2) {
				Position positionToTarget = new Position(Letter.values()[i], Number.values()[j]);
				targetList.add(positionToTarget);
			}
		}
		for (int i = 1; i < Number.values().length; i = i + 2) {
			for (int j = 1; j < Letter.values().length; j = j + 2) {
				Position positionToTarget = new Position(Letter.values()[i], Number.values()[j]);
				targetList.add(positionToTarget);
			}
		}
		state = ShooterState.Random;

	}

	public Position shoot() {
		Boolean isRelevant = false;
		Position position = null;
		if (getState() == ShooterState.Random) {
			while (!isRelevant) {
				int randomIndex = (int) Math.floor(Math.random() * targetList.size());
				position = targetList.get(randomIndex);
				targetList.remove(randomIndex);
				isRelevant = isCaseRelevant(position);
			}
		} else if (getState() == ShooterState.Touche) {
			int randomIndex = (int) Math.floor(Math.random() * surroundingToucheList.size());
			position = surroundingToucheList.get(randomIndex);
			surroundingToucheList.remove(randomIndex);
			// voir pour implémenter is relevant, sans prendre en compte le bateau en
			// cours...
		} else if (getState() == ShooterState.ToucheOrientation) {
			if (orientationTouche == Orientation.Vertical) {
				// si la dernière fois c'était dans l'eau on change de sens
				if (caseList.get(lastTarget).getCaseStatus() == CaseStatus.Eau) {
					if (positionFirstTouche.getValeurCoordLigne() > lastTarget.getValeurCoordLigne()) {
						position = new Position(Letter.values()[positionFirstTouche.getValeurCoordLigne() + 1],
								positionFirstTouche.getNomCoordColonne());
					} else {
						position = new Position(Letter.values()[positionFirstTouche.getValeurCoordLigne() - 1],
								positionFirstTouche.getNomCoordColonne());
					}
					// si on touche toujours on continue
				} else {
					if (positionFirstTouche.getValeurCoordLigne() > lastTarget.getValeurCoordLigne()) {
						// on teste si on est au bord de la grille, sinon on change de sens
						if (lastTarget.getValeurCoordLigne() - 1 >= 0) {
							position = new Position(Letter.values()[lastTarget.getValeurCoordLigne() - 1],
									lastTarget.getNomCoordColonne());
						} else {
							position = new Position(Letter.values()[positionFirstTouche.getValeurCoordLigne() + 1],
									lastTarget.getNomCoordColonne());
						}
					} else {
						if (lastTarget.getValeurCoordLigne() + 1 < Letter.values().length) {
							position = new Position(Letter.values()[lastTarget.getValeurCoordLigne() + 1],
									lastTarget.getNomCoordColonne());
						} else {
							position = new Position(Letter.values()[positionFirstTouche.getValeurCoordLigne() - 1],
									lastTarget.getNomCoordColonne());
						}
					}

				}
			} else if (orientationTouche == Orientation.Horizontal) {
				if (caseList.get(lastTarget).getCaseStatus() == CaseStatus.Eau) {
					if (positionFirstTouche.getValeurCoordColonne() > lastTarget.getValeurCoordColonne()) {
						position = new Position(positionFirstTouche.getNomCoordLigne(),
								Number.values()[positionFirstTouche.getValeurCoordColonne() + 1]);
					} else {
						position = new Position(positionFirstTouche.getNomCoordLigne(),
								Number.values()[positionFirstTouche.getValeurCoordColonne() - 1]);
					}
				} else {
					if (positionFirstTouche.getValeurCoordColonne() > lastTarget.getValeurCoordColonne()) {
						if (lastTarget.getValeurCoordColonne() - 1 >= 0) {
							position = new Position(lastTarget.getNomCoordLigne(),
									Number.values()[lastTarget.getValeurCoordColonne() - 1]);
						} else {
							position = new Position(lastTarget.getNomCoordLigne(),
									Number.values()[positionFirstTouche.getValeurCoordColonne() + 1]);
						}
					} else {
						if (lastTarget.getValeurCoordColonne() + 1 < Number.values().length) {
							position = new Position(lastTarget.getNomCoordLigne(),
									Number.values()[lastTarget.getValeurCoordColonne() + 1]);
						} else {
							position = new Position(lastTarget.getNomCoordLigne(),
									Number.values()[positionFirstTouche.getValeurCoordColonne() - 1]);
						}
					}

				}
			}
		}
		return position;
	}

	public void updateCase(Position position, CaseStatus caseStatus) {
		Case caseToUpdate = caseList.get(position);
		caseToUpdate.setCaseStatus(caseStatus);
		setLastTarget(position);
		if (caseStatus == CaseStatus.Touche) {
			if (getState() == ShooterState.Touche) {
				setState(ShooterState.ToucheOrientation);
				setOrientationTouche(position);
			} else if (getState() == ShooterState.Random) {
				setState(ShooterState.Touche);
				setPositionFirstTouche(position);
				setSurroundingToucheList(position);
			}
		}
		if (caseStatus == CaseStatus.Coule) {
			setNbCoule(getNbCoule() + 1);
			setState(ShooterState.Random);
		}
	}

	public boolean isCaseRelevant(Position position) {
		Case caseToTest = caseList.get(position);
		if (caseToTest.getCaseStatus() != CaseStatus.Libre) {
			return false;
		}
		List<Position> surroundingPositions = getSurroundingPositions(position);
		for (Position surroundingPosition : surroundingPositions) {
			Case surroundingCase = caseList.get(surroundingPosition);
			if (surroundingCase.getCaseStatus() == CaseStatus.Touche
					|| surroundingCase.getCaseStatus() == CaseStatus.Coule) {
				return false;
			}
		}
		return true;
	}

	public List<Position> getSurroundingPositions(Position position) {
		List<Position> surroundingPositions = new ArrayList<>();
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				int indexColonne = position.getValeurCoordColonne() + i;
				int indexLigne = position.getValeurCoordLigne() + j;
				if (indexLigne < Letter.values().length && indexColonne < Number.values().length && indexColonne >= 0
						&& indexLigne >= 0) {
					Position surroundingPosition = new Position(Letter.values()[indexLigne],
							Number.values()[indexColonne]);
					surroundingPositions.add(surroundingPosition);
				}
			}
		}
		return surroundingPositions;
	}

	public void showGrid() {
		caseList.forEach((position, currentCase) -> {
			if (currentCase.getCaseStatus() == CaseStatus.Libre) {
				System.out.print(EMPTY_CASE);
			} else if (currentCase.getCaseStatus() == CaseStatus.Eau) {
				System.out.print(WATER_CASE);
			} else {
				System.out.print(BOAT_CASE);
			}
			if (currentCase.getPosition().getNomCoordColonne() == Number.DIX) {
				System.out.println(" ");
			}
		});
	}

	// ----------------------------
	// -- GETTER SETTER -----------
	// ----------------------------
	public int getNbCoule() {
		return nbCoule;
	}

	public void setNbCoule(int nbCoule) {
		this.nbCoule = nbCoule;
	}

	public ShooterState getState() {
		return state;
	}

	public void setState(ShooterState state) {
		this.state = state;
	}

	public Position getPositionFirstTouche() {
		return positionFirstTouche;
	}

	public void setPositionFirstTouche(Position positionFirstTouche) {
		this.positionFirstTouche = positionFirstTouche;
	}

	public List<Position> getSurroundingToucheList() {
		return surroundingToucheList;
	}

	public void setSurroundingToucheList(Position position) {
		surroundingToucheList = new ArrayList<>();
		if (position.getValeurCoordLigne() - 1 >= 0) {
			Letter northLigne = Letter.values()[(position.getValeurCoordLigne()) - 1];
			Number northColonne = position.getNomCoordColonne();
			Position northPosition = new Position(northLigne, northColonne);
			surroundingToucheList.add(northPosition);
		}
		if (position.getValeurCoordLigne() + 1 < Letter.values().length) {
			Letter southLigne = Letter.values()[(position.getValeurCoordLigne()) + 1];
			Number southColonne = position.getNomCoordColonne();
			Position southPosition = new Position(southLigne, southColonne);
			surroundingToucheList.add(southPosition);
		}
		if (position.getValeurCoordColonne() - 1 >= 0) {
			Letter westLigne = position.getNomCoordLigne();
			Number westColonne = Number.values()[position.getValeurCoordColonne() - 1];
			Position westPosition = new Position(westLigne, westColonne);
			surroundingToucheList.add(westPosition);
		}
		if (position.getValeurCoordColonne() + 1 < Number.values().length) {
			Letter eastLigne = position.getNomCoordLigne();
			Number eastColonne = Number.values()[position.getValeurCoordColonne() + 1];
			Position eastPosition = new Position(eastLigne, eastColonne);
			surroundingToucheList.add(eastPosition);
		}
	}

	public Orientation getOrientationTouche() {
		return orientationTouche;
	}

	public void setOrientationTouche(Position position) {
		if (position.getNomCoordLigne() == positionFirstTouche.getNomCoordLigne()) {
			orientationTouche = Orientation.Horizontal;
		}
		if (position.getNomCoordColonne() == positionFirstTouche.getNomCoordColonne()) {
			orientationTouche = Orientation.Vertical;
		}
	}

	public Position getLastTarget() {
		return lastTarget;
	}

	public void setLastTarget(Position lastTarget) {
		this.lastTarget = lastTarget;
	}

	public List<Position> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<Position> targetList) {
		this.targetList = targetList;
	}
}
