package naval;

public enum Number {
	UN(0), DEUX(1), TROIS(2), QUATRE(3), CINQ(4), SIX(5), SEPT(6), HUIT(7), NEUF(8), DIX(9);

	private int valeurDeColonne;

	private Number(int valeurDeColonne) {
		this.valeurDeColonne = valeurDeColonne;
	}

	public int getValeurDeColonne() {
		return valeurDeColonne;
	}
}
