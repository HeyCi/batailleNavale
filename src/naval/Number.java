package naval;

public enum Number {
	UN(1), DEUX(2), TROIS(3), QUATRE(4), CINQ(5), SIX(6), SEPT(7), HUIT(8), NEUF(9), DIX(10);

	private int valeurDeColonne;

	private Number(int valeurDeColonne) {
		this.valeurDeColonne = valeurDeColonne;
	}

	public int getValeurDeColonne() {
		return valeurDeColonne;
	}
}
