package adrar.bataille.ui.Enums;

public enum ShipType {
	PorteAvion(5), Croiseur(4), ContreTorpilleur(3), SousMarin(3), Torpilleur(2);
	
	private int shipSize;
	
	ShipType(int shipSize)
	{
		this.shipSize = shipSize;
	}
	
	public int getShipSize()
	{
		return this.shipSize;
	}
}
