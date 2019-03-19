package naval;

public class BattleTest {

	public static void main(String[] args) {
		Grid grid = new Grid();
		grid.placeBoat();
		grid.placeBoat2(BoatType.PorteAvion, Orientation.Vertical);
		grid.showGrid();
	}

}
