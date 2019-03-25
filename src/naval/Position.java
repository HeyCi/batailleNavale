package naval;

public class Position {
	private Letter nomCoordLigne;
	private Number nomCoordColonne;

	@Override
	public boolean equals(Object arg0) {
		if (arg0 == null) {
			return false;
		} else {
			Position positionToCompare = (Position) arg0;
			if (this == positionToCompare) {
				return true;
			}
			if (nomCoordColonne == positionToCompare.nomCoordColonne
					&& nomCoordLigne == positionToCompare.nomCoordLigne) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public int hashCode() {
		int hashCode = getValeurCoordLigne() * Letter.values().length + getValeurCoordColonne();
		return hashCode;
	}

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
