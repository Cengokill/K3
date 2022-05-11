package Reseau;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	int port_client;
	ServerSocket ss;
	Socket s;
	String nom_client;
	String s1;
	DataOutputStream out;

	public void main(String arcs[]) throws IOException {
		this.port_client = 1997;
		this.ss = new ServerSocket(port_client);
		System.out.println("En attente de connection du client...");
		this.s = ss.accept();// connection au socket d'ecoute
		System.out.println("Connecte !");
		// recuperation de la donnee client
		DataInputStream in = new DataInputStream(s.getInputStream());
		this.nom_client = in.readUTF();
		// traitement
		this.s1 = "Bienvenue " + nom_client + ". Tu est desormais connecte.";
		// envoi de la donnee au client
		this.out = new DataOutputStream(s.getOutputStream());
		this.out.writeUTF(s1);
	}

}
