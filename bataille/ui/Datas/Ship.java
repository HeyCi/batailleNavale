package adrar.bataille.ui.Datas;

import adrar.bataille.ui.Enums.ShipDirection;

public class Ship {
	
	private GridPosition[] positions;
	private ShipDirection direction;
	private int size;
	
	public Ship(ShipDirection direction, int size)
	{
		this.direction = direction;
		this.size = size;
	}
	
	public void setPositions(GridPosition[] positions)
	{
		this.positions = positions;
		
		for (int i = 0; i < positions.length; i++)
			positions[i].setShip(this);
	}
}
