package adrar.bataille.ui.Abstract;

import java.awt.Color;

import adrar.bataille.ui.bataillenavale;
import adrar.bataille.ui.Datas.*;
import adrar.bataille.ui.Enums.*;

public abstract class AbstractIA {
	
	private String name;
	private Color caseColor;
	private bataillenavale ui;
	private ShipType currentShipToSpawn;
	
	public AbstractIA(bataillenavale ui, String name, Color caseColor)
	{
		this.ui = ui;
		this.ui.ia = this;
		this.ui.grid.ui = this.ui;
		this.name = name;
		this.caseColor = caseColor;
		this.currentShipToSpawn = ShipType.PorteAvion;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public bataillenavale getUI()
	{
		return this.ui;
	}
	
	public GridPosition returnPosition(Letter letter, GridNumber number)
	{
		return getUI().grid.getPosition(letter,  number);
	}
	
	public ShipType getShipToSpawn()
	{
		return this.currentShipToSpawn;
	}
	
	public void nextShipToSpawn()
	{		
		switch (currentShipToSpawn)
		{
			case PorteAvion:
				currentShipToSpawn = ShipType.Croiseur;
			break;
			case Croiseur:
				currentShipToSpawn = ShipType.ContreTorpilleur;
			break;
			case ContreTorpilleur:
				currentShipToSpawn = ShipType.SousMarin;
			break;
			case SousMarin:
				currentShipToSpawn = ShipType.Torpilleur;
			break;
			case Torpilleur:
				currentShipToSpawn = null;
			break;
		}
	}
	
	public Color getCaseColor()
	{
		return caseColor;
	}
	
	public void addShip(Ship ship)
	{
		ship.setParent(this);
		
		GridPosition[] positions = ship.getPositions();
		
		for (int i = 0; i < positions.length; i++) {
			positions[i].getPanel().setBackground(getCaseColor());
		}
	}
	
}
