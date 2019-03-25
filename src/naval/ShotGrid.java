package naval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShotGrid {
	private static final String BOAT_CASE = "X ";
	private static final String EMPTY_CASE = ". ";
	private static final String WATER_CASE = "O ";

	private Map<Position, Case> caseList;
	private int nbCoule;
	private Position lastTryPosition;
	// private Position lastShotPosition;

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
	}

	public Position shoot() {
		if (lastTryPosition == null) {
			Position position = new Position(Letter.B, Number.TROIS);
			return position;
		}
		// TODO implementer (liste toute faite tant que libre ou quand coulé, si touché
		// à écrire aussi)
		return null;
	}

	public void updateCase(Position position, CaseStatus caseStatus) {
		Case caseToUpdate = caseList.get(position);
		caseToUpdate.setCaseStatus(caseStatus);
		if (caseStatus == CaseStatus.Coule) {
			setNbCoule(getNbCoule() + 1);
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
				int indexLigne = position.getValeurCoordLigne() + i;
				if (indexColonne < Letter.values().length && indexLigne < Number.values().length) {
					Position surroundingPosition = new Position(Letter.values()[indexColonne],
							Number.values()[indexLigne]);
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

	public int getNbCoule() {
		return nbCoule;
	}

	public void setNbCoule(int nbCoule) {
		this.nbCoule = nbCoule;
	}
}
