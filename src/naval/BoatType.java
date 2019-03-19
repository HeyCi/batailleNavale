package naval;

public enum BoatType {
	PorteAvion(5), Croiseur(4), ContreTorpilleur(3), SousMarin(3), Torpilleur(2);

	private int taille;

	private BoatType(int taille) {
		this.taille = taille;
	}

	public int getTaille() {
		return taille;
	}
}
