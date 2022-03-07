package persistance.utilisateur;

import mediatek2022.Utilisateur;

public class UtilisateurMediatek implements Utilisateur{
	
	private int id;
	private String nom; 
	private String prenom;
	private String pseudo; 
	private String role; 
	private int age; 
		
	public UtilisateurMediatek(int idU, String nomU, String prenomU, String pseudoU, String roleU, int ageU) {
		this.id = idU;
		this.nom = nomU; 
		this.prenom = prenomU; 
		this.pseudo = pseudoU; 
		this.role = roleU; 
		this.age = ageU; 
	}

	@Override
	public String name() {
		return pseudo; 
	}

	@Override
	public boolean isBibliothecaire() {
		if(role.equals("bibliothecaire")) {
			return true; 
		}
		return false; 
	}

	@Override
	public Object[] data() {
		Object[] obj = {id, nom, prenom, pseudo, role, age };
		return obj;
	}

	@Override
	public String toString() {
		return "UtilisateurMediatek [nom=" + nom + ", prenom=" + prenom + ", pseudo=" + pseudo + ", role=" + role
				+ ", age=" + age + "]";
	}
}
