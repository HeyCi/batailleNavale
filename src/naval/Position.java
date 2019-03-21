package naval;

public class Position {
	private Letter nomCoordLigne;
	private Number nomCoordColonne;

	public Position(Letter nomCoordLigne, Number nomCoordColonne) {
		setNomCoordLigne(nomCoordLigne);
		setNomCoordColonne(nomCoordColonne);
	}

	public int getValeurCoordLigne() {
		return nomCoordLigne.getValeurDeLigne();
	}

	public int getValeurCoordColonne() {
		return nomCoordColonne.getValeurDeColonne();
	}

	public Letter getNomCoordLigne() {
		return nomCoordLigne;
	}

	public void setNomCoordLigne(Letter coordLigne) {
		nomCoordLigne = coordLigne;
	}

	public Number getNomCoordColonne() {
		return nomCoordColonne;
	}

	public void setNomCoordColonne(Number coordColonne) {
		nomCoordColonne = coordColonne;
	}
}
