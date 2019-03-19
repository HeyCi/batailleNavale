package adrar.bataille.ui.Abstract;

import java.awt.Color;

import adrar.bataille.ui.Datas.*;
import adrar.bataille.ui.Enums.*;

public class AbstractIA {
	
	private String name;
	private Color caseColor;
	
	public AbstractIA(String name, Color caseColor)
	{
		this.name = name;
		this.caseColor = caseColor;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public GridPosition returnPosition(Letter letter, GridNumber number)
	{
		return Grid.instance.getPosition(letter,  number);
	}
	
}
