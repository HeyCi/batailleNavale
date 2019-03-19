package adrar.bataille.ui.Datas;

import java.awt.Cursor;
import java.util.HashMap;
import java.util.Map;

import adrar.bataille.ui.bataillenavale;
import adrar.bataille.ui.Enums.*;

public class Grid {
	
	public bataillenavale ui;
	private Map<String, GridPosition> caseMap;
	
	public Grid() {		
		caseMap = new HashMap<String, GridPosition>();
        for (GridNumber number : GridNumber.values()) {
            for (Letter letter : Letter.values()) {
            	GridPosition position = new GridPosition(this, letter, number);
            	caseMap.put(position.getKey(), position);
            }
        }
	}
	
	public void activeSelection()
	{
		for (GridPosition position : caseMap.values())
		{
			position.getPanel().setEnabled(true);
			position.getPanel().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
		if (!isValidCase(position, direction))
			return null;
		
		GridPosition[] positions = new GridPosition[shipType.getShipSize()];
		GridPosition currentPosition = position;
		
		positions[0] = position;

		for (int i = 1; i < shipType.getShipSize(); i++)
		{			
			if (direction == ShipDirection.Horizontal)
				currentPosition = getPosition(getNextLetter(currentPosition.getLetter()), currentPosition.getNumber());
			else
				currentPosition = getPosition(currentPosition.getLetter(), getNextNumber(currentPosition.getNumber()));
			
			if (!isValidCase(currentPosition, direction))
				return null;
			positions[i] = currentPosition;
		}
		
		return positions;
	}
	public Ship createShip(GridPosition startPosition, ShipDirection direction, ShipType shipType){
		GridPosition[] positions = getShipPositions(startPosition, direction, shipType);
		
		if (positions != null) {
			Ship ship = new Ship(direction, shipType);
			ship.setPositions(positions);
			
			return ship;
		}
		else {
			System.out.println("Invalid position selection");
			return null;
		}
	}
	
	private boolean isValidCase(GridPosition position, ShipDirection direction)
	{
		if (position == null || position.isUsed())
			return false;
		
		GridPosition[] positionsToCheck = new GridPosition[] {
				getPosition(getBeforeLetter(position.getLetter()), getBeforeNumber(position.getNumber())),
				getPosition(getBeforeLetter(position.getLetter()), position.getNumber()),
				getPosition(getBeforeLetter(position.getLetter()), getNextNumber(position.getNumber())),
				getPosition(position.getLetter(), getBeforeNumber(position.getNumber())),
				getPosition(position.getLetter(), getNextNumber(position.getNumber())),
				getPosition(getNextLetter(position.getLetter()), getBeforeNumber(position.getNumber())),
				getPosition(getNextLetter(position.getLetter()), position.getNumber()),
				getPosition(getNextLetter(position.getLetter()), getNextNumber(position.getNumber()))
		};
		
		for (int i = 0; i < positionsToCheck.length; i++) {
			if (positionsToCheck[i] != null && positionsToCheck[i].isUsed())
				return false;
		}
		
		return true;
	}
	
	private Letter getNextLetter(Letter current)
	{
		return getLetter(current, 1);
	}
	private Letter getBeforeLetter(Letter current)
	{
		return getLetter(current, -1);
	}
	private Letter getLetter(Letter current, int dirNumber)
	{
		Letter[] values = Letter.values();
		
		for (int i = 0; i < values.length; i++)
		{
			if (values[i].getValue() == current.getValue() + dirNumber)
				return values[i];
		}
		return null;
	}
	
	private GridNumber getNextNumber(GridNumber current)
	{
		return getNumber(current, 1);	
	}
	private GridNumber getBeforeNumber(GridNumber current)
	{
		return getNumber(current, -1);
	}
	private GridNumber getNumber(GridNumber current, int dirNumber)
	{
		GridNumber[] values = GridNumber.values();
		
		for (int i = 0; i < values.length; i++)
		{
			if (values[i].getValue() == current.getValue() + dirNumber)
				return values[i];
		}
		return null;
	}
}
