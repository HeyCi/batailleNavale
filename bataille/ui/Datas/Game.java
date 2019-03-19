package adrar.bataille.ui.Datas;

import java.awt.Color;

import adrar.bataille.ui.bataillenavale;

public class Game {

	public static void main(String[] args)
	{		
		IA ia1 = new IA(getNewUI(), "IA-1", Color.green);
		IA ia2 = new IA(getNewUI(), "IA-2", Color.blue);
		
		ia1.getUI().createShipsForIA();
		ia2.getUI().createShipsForIA();
		
		//ia1.getUI().handSelection();
		//ia2.getUI().handSelection();
	}
	
	public static bataillenavale getNewUI()
	{
		bataillenavale window = new bataillenavale();
		window.GenerateMap();
		window.frame.setVisible(true);

		return window;
	}
}
