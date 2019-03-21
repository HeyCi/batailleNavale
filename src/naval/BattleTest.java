package naval;

public class BattleTest {

	public static void main(String[] args) {
		Grid grid = new Grid();
		grid.placeBoat(BoatType.PorteAvion);
		grid.placeBoat(BoatType.SousMarin);
		grid.showGrid();
		System.out.println(grid.phraseTest);
	}

}
