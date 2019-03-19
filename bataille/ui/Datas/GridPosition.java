package adrar.bataille.ui.Datas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import adrar.bataille.ui.Enums.*;

public class GridPosition {

	private Grid grid;
	private Letter letter;
	private GridNumber number;
	private JPanel panelUI;
	private Ship shipInCase;
	
	public GridPosition(Grid grid, Letter letter, GridNumber number)
	{
		this.grid = grid;
		this.letter = letter;
		this.number = number;
	}
	
	public void setPanel(JPanel panel)
	{
		this.panelUI = panel;
		
		this.panelUI.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				onSelectCase(arg0);
			}
		});
	}
	
	public JPanel getPanel()
	{
		return this.panelUI;
	}
	public String getKey()
	{
		return letter.toString() + number.getValue();
	}
	
	public Letter getLetter()
	{
		return this.letter;
	}
	public GridNumber getNumber()
	{
		return this.number;
	}
	
	public boolean isUsed()
	{
		return this.shipInCase != null;
	}
	
	public void setShip(Ship ship)
	{
		this.shipInCase = ship;
	}
	public Ship getShip()
	{
		return this.shipInCase;
	}

	private void onSelectCase(MouseEvent evt)
	{
		ShipDirection direction = ShipDirection.Horizontal;
		ShipType shipToSpawn;
		
		if (evt.getButton() == 3)
			direction = ShipDirection.Vertical;
		
		shipToSpawn = grid.ui.ia.getShipToSpawn();
		if (shipToSpawn == null)
			return;

		Ship ship = grid.createShip(this, direction, shipToSpawn);
		
		if (ship == null)
			return;
		
		grid.ui.ia.nextShipToSpawn();
		grid.ui.ia.addShip(ship);
	}
}
