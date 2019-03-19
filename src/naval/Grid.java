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
}
