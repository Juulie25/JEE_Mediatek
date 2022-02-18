package mediatek2022;

public interface Document {

	boolean disponible();
	void emprunt(Utilisateur u) throws Exception;
	void retour();
	// l'affichage d'un document utilisera toString()
}
