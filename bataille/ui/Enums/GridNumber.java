package adrar.bataille.ui.Enums;

public enum GridNumber {
	One(1), Two(2), Three(3), Four(4), Five(5), Six(6), Seven(7), Eight(8), Nine(9), Ten(10);
	
	private int number;
	
	GridNumber(int number)
	{
		this.number = number;
	}
	
	public int getValue()
	{
		return this.number;
	}
}
