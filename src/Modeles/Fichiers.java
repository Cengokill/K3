package Modeles;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Controleur.Jeu;

public class Fichiers {
	private File f;
	public int NB_LIGNES_SAUVEGARDE = 33;

	public Fichiers(String texte) {// texte contient le chemin et le nom du fichier
		f = new File(texte);
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}



	public Jeu lisSauvegarde() {
		ArrayList<String> tab = new ArrayList<String>();
		PyramideMontagne pm = new PyramideMontagne(9, 9);
		try {
			FileReader reader = new FileReader(f);
			BufferedReader br = new BufferedReader(reader);
			String ligne_lue;
			while ((ligne_lue = br.readLine()) != null) {
				tab.add(ligne_lue);
			}
			if (tab.size() != NB_LIGNES_SAUVEGARDE) {// NB DE LIGNES DU FICHIER DE SAUVEGARDE = 7
				System.err.println("Erreur : le fichier de sauvegarde est corrompu." + tab.size());
				return null;
			}
			String[] ligneTab = tab.get(0).split(":");
			int hauteurMont = Integer.parseInt(ligneTab[1]);
			ligneTab = tab.get(1).split(":");
			int hauteurJou = Integer.parseInt(ligneTab[1]);
			Piece p = stringToPiece(tab.get(3));
			Position pos = new Position(0, 8);
			pm.ajouter(p, pos);

			ligneTab = tab.get(4).split("");
			p = stringToPiece(ligneTab[0]);
			pos = new Position(0, 7);
			pm.ajouter(p, pos);

			p = stringToPiece(ligneTab[1]);
			pos = new Position(1, 7);
			pm.ajouter(p, pos);

			ligneTab = tab.get(5).split("");
			p = stringToPiece(ligneTab[0]);
			pos = new Position(0, 6);
			pm.ajouter(p, pos);

			p = stringToPiece(ligneTab[1]);
			pos = new Position(1, 6);
			pm.ajouter(p, pos);

			p = stringToPiece(ligneTab[2]);
			pos = new Position(2, 6);
			pm.ajouter(p, pos);

			br.close();
			reader.close();
			return new Sauvegarde();
		} catch (IOException e) {
			System.err.println("Erreur : le fichier de sauvegarde est corrompu.");
			e.printStackTrace();
		}
		return null;
	}

	public Piece stringToPiece(String s) {
		Couleurs c;
		switch (s) {
			case "B":
				c = Couleurs.BLEU;
				break;
			case "N":
				c = Couleurs.NOIR;
				break;
			case "R":
				c = Couleurs.ROUGE;
				break;
			case "V":
				c = Couleurs.VERT;
				break;
			case "J":
				c = Couleurs.JAUNE;
				break;
			case "W":
				c = Couleurs.BLANC;
				break;
			case "#":
				c = Couleurs.NATUREL;
				break;
			default:
				return null;
		}
		Piece p = new Piece(c);
		return p;
	}

	public void ecrireStats(Joueur j, int nbP, int nbV) {
		if (typeFichier == 1) {
			try {
				FileWriter writer = new FileWriter(f, false);// �crire en mode remplacement
				BufferedWriter bw = new BufferedWriter(writer);
				bw.write("numJ:" + String.valueOf(j.numeroJoueur));
				bw.newLine();
				bw.write("typeJ:" + String.valueOf(j.typeJ));
				bw.newLine();
				bw.write("nombre de parties jouees:" + String.valueOf(nbP));
				bw.newLine();
				bw.write("nombre de victoires:" + String.valueOf(nbV));
				bw.newLine();
				bw.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.err.println("Erreur : le fichier � �crire n'est pas un fichier de statistiques.");
		}
	}

	public Statistiques lisStats() {
		if (typeFichier == 1) {
			ArrayList<String> tab = new ArrayList<String>();
			try {
				FileReader reader = new FileReader(f);
				BufferedReader br = new BufferedReader(reader);
				String ligne_lue;
				while ((ligne_lue = br.readLine()) != null) {
					tab.add(ligne_lue);
				}
				if (tab.size() != 4) {// NB DE LIGNES DU FICHIER DE SAUVEGARDE = 4
					System.err.println("Erreur : le fichier de statistiques du joueur est corrompu.");
					return null;
				}
				String[] ligne = tab.get(0).split(":");
				Joueur j = new Joueur(Integer.parseInt(ligne[1]));
				ligne = tab.get(1).split(":");
				j.typeJ = Integer.parseInt(ligne[1]);
				ligne = tab.get(2).split(":");
				int nbP = Integer.parseInt(ligne[1]);
				ligne = tab.get(3).split(":");
				int nbV = Integer.parseInt(ligne[1]);
				br.close();
				reader.close();
				return new Statistiques(j, nbP, nbV);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} else {
			System.err.println("Erreur : le fichier n'est pas un fichier de statistiques.");
			return null;
		}
	}

	public static void ecrireRemplace(File fichier, String texte) {
		try {
			FileWriter writer = new FileWriter(fichier, false);// �crire en mode remplacement
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(texte);
			bw.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void ecrireAjout(File fichier, String texte) {
		try {
			FileWriter writer = new FileWriter(fichier, true);// �crire en mode ajout
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write(texte);
			bw.newLine();// saut de ligne
			bw.close();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static File createFile(String chemin) throws IOException {
		File fichier = new File(chemin);
		if (!fichier.exists()) {
			try {
				fichier.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FileWriter writer = new FileWriter(fichier);
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("");
			bw.close();
			writer.close();
		}
		return fichier;
	}
}
