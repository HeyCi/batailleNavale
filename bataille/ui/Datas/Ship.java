package adrar.bataille.ui.Datas;

import adrar.bataille.ui.Abstract.AbstractIA;
import adrar.bataille.ui.Enums.*;

public class Ship {
	
	private GridPosition[] positions;
	private ShipDirection direction;
	private ShipType shipType;
	private AbstractIA parent;
	
	public Ship(ShipDirection direction, ShipType shipType)
	{
		this.direction = direction;
		this.shipType = shipType;
	}
	
	public void setPositions(GridPosition[] positions)
	{
		this.positions = positions;
		
		for (int i = 0; i < positions.length; i++)
			positions[i].setShip(this);
	}
	public GridPosition[] getPositions()
	{
		return this.positions;
	}
	
	public void setParent(AbstractIA parent)
	{
		this.parent = parent;
	}
}
