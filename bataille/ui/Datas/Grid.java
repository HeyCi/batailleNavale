package adrar.bataille.ui.Datas;

import java.util.HashMap;
import java.util.Map;

import adrar.bataille.ui.Enums.GridNumber;
import adrar.bataille.ui.Enums.Letter;
import adrar.bataille.ui.Enums.ShipDirection;
import adrar.bataille.ui.Enums.ShipType;

public class Grid {

	public static Grid instance;
	
	private Map<String, GridPosition> caseMap;
	
	public Grid() {
		instance = this;
		
		caseMap = new HashMap<String, GridPosition>();
        for (GridNumber number : GridNumber.values()) {
            for (Letter letter : Letter.values()) {
            	GridPosition position = new GridPosition(letter, number);
            	caseMap.put(position.getKey(), position);
            }
        }
	}
	
	public GridPosition getPosition(Letter letter, GridNumber number)
	{
		if (letter == null || number == null)
			return null;
		
		String key = letter.toString() + number.getValue();
		if (caseMap.containsKey(key)) {
			return caseMap.get(key);
		}
		
		return null;
	}
	
	public GridPosition[] getShipPositions(GridPosition position, ShipDirection direction, ShipType shipType)
	{
		GridPosition[] positions = new GridPosition[shipType.getShipSize()];
		GridPosition currentPosition = position;
		
		for (int i = 0; i < shipType.getShipSize(); i++)
		{			
			if (direction == ShipDirection.Horizontal) {
				currentPosition = getPosition(getNextLetter(currentPosition.getLetter()), currentPosition.getNumber());

				if (currentPosition == null)
					return null;
				positions[i] = currentPosition;
			}
			else {
				currentPosition = getPosition(currentPosition.getLetter(), getNextNumber(currentPosition.getNumber()));

				if (currentPosition == null)
					return null;
				positions[i] = currentPosition;
			}
		}
		
		return positions;
	}
	public Ship createShip(GridPosition startPosition, ShipDirection direction, ShipType shipType){
		GridPosition[] positions = getShipPositions(startPosition, direction, shipType);
		
		if (positions != null) {
			Ship ship = new Ship(direction, shipType.getShipSize());
			ship.setPositions(positions);
			
			return ship;
		}
		else {
			System.out.println("Invalid position selection");
			return null;
		}
	}
	
	private Letter getNextLetter(Letter current)
	{
		Letter[] values = Letter.values();
		
		for (int i = 0; i < values.length; i++)
		{
			if (values[i].getValue() == current.getValue() + 1)
				return values[i];
		}
		return null;
	}
	private GridNumber getNextNumber(GridNumber current)
	{
		GridNumber[] values = GridNumber.values();
		
		for (int i = 0; i < values.length; i++)
		{
			if (values[i].getValue() == current.getValue() + 1)
				return values[i];
		}
		return null;
	}
}
