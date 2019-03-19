package naval;

public enum Letter {
	A(1), B(2), C(3), D(4), E(5), F(6), G(7), H(8), I(9), J(10);

	private int valeurDeLigne;

	private Letter(int valeurDeLigne) {
		this.valeurDeLigne = valeurDeLigne;
	}

	public int getValeurDeColonne() {
		return valeurDeLigne;
	}
}
