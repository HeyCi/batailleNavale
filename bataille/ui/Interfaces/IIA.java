package adrar.bataille.ui.Interfaces;

import adrar.bataille.ui.Datas.GridPosition;
import adrar.bataille.ui.Enums.*;

public interface IIA {

	public GridPosition getCustomAttackPosition();
	public GridPosition createShip(ShipDirection direction, ShipType shipType);
	public void attackCase(GridPosition position);
}
