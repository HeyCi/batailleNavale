package naval;

public class BattleTest {

	public static void main(String[] args) {
		Game game = new Game();
		game.placeBoat(BoatType.SousMarin);
		game.showGrid();
	}

}
