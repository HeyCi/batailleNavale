package naval;

public class BattleTest {

	public static void main(String[] args) {
		Grid grid = new Grid();
		grid.placeBoat(BoatType.PorteAvion, Orientation.Vertical);
		grid.showGrid();
	}

}
