package naval;

public enum Letter {
	A(0), B(1), C(2), D(3), E(4), F(5), G(6), H(7), I(8), J(9);

	private int valeurDeLigne;

	private Letter(int valeurDeLigne) {
		this.valeurDeLigne = valeurDeLigne;
	}

	public int getValeurDeLigne() {
		return valeurDeLigne;
	}
}
