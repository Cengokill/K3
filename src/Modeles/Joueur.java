package Modeles;

import java.util.ArrayList;
//import java.util.Scanner;

public class Joueur extends Acteur {
	private PiecePyramide piecePosee;
	private Coup CoupDemande;
	private PiecePyramide choixVol;

	public Joueur(String nom) {
		super(nom);
	}

	public Coup jouer(ArrayList<Coup> arr, Partie p) {
		// a retirer ------------------------------------- 
		/*
		super.afficherCoupsJouables(arr);
		Scanner myObj = new Scanner(System.in);// NE PAS CLOSE() myObj
		String coupString = myObj.nextLine();
		Coup c = arr.get(Integer.parseInt(coupString));
		*/
		// a ajouter -------------------------------------
		//Coup c = CoupDemander; 
		validerCoup=false;
		return CoupDemande;
	}

	public PiecePyramide choixVol(ArrayList<PiecePyramide> arr, Partie p) {// deja des pieces volables
		// a retirer ------------------------------------- 
		/*
		Scanner myObj = new Scanner(System.in);// NE PAS CLOSE() myObj
		String num = myObj.nextLine();
		int rep = Integer.parseInt(num);
		if (rep == 0) {
			System.out.println("=========== VOL DE PIECE ===========");
			System.out.println("Camp du joueur victime :");
			// System.out.println(victime.getCamp().toString());
			System.out.println("Ses pieces volees :"); // + victime.toStringPiecesVolees()
			ArrayList<PiecePyramide> piecesVolables = arr;
			System.out.println(super.getNom() + ", veuillez choisir une piece a voler :");

			for (int i = 0; i < arr.size(); i++) {
				System.out.println("[" + i + "]" + arr.get(i).toString());
			}
			Scanner myObj2 = new Scanner(System.in);// NE PAS CLOSE() myObj
			int num2 = myObj2.nextInt();
			return arr.get(num2);
		
		} else {
			return null;
		}
		*/
		// a ajouter -------------------------------------
		validerCoup=false;
		return choixVol;
	}

	public ArrayList<PiecePyramide> phase1(Partie encours) {
		ArrayList<PiecePyramide> arr = new ArrayList<PiecePyramide>();
		if (this.piecePosee != null) {
			arr.add(this.piecePosee);
			this.piecePosee = null;
		}
		return arr;
	}

	public String toStringArrPos(ArrayList<Position> arr) {
		String chaine = new String();
		String carSep;
		for (int i = 0; i < arr.size(); i++) {
			if (i < arr.size() - 1) {
				carSep = "  |  ";
			} else {
				carSep = "";
			}
			chaine += "[" + i + "] : " + arr.get(i).toString() + carSep;
		}
		return chaine;
	}

	public void setPiecesPosees(PiecePyramide p) {
		this.piecePosee = p;
	}
	
	public void setCoupDemande(Coup c) {
		this.CoupDemande = c;
	}
	
	public void setChoixVol(PiecePyramide choixVol) {
		this.choixVol = choixVol;
	}

}