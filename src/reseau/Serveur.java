package Reseau;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	int port_client, difficulte;
	ServerSocket serveurSocket;
	Socket clientSocket;
	String nom_serveur, nom_client;
	String message_bienvenue;
	DataOutputStream out;
	//PrintWriter out;
	BufferedReader in;
	String inputLine;
	boolean ready;

	public Serveur(String nomJ, int difficulte) throws IOException {
		this.ready=false;
		this.nom_serveur=nomJ;
		this.difficulte=difficulte;
		this.port_client = 1997;
		try {
			this.serveurSocket = new ServerSocket(port_client);
			System.out.println("En attente de connection du client...");
			this.clientSocket = serveurSocket.accept();// connection au socket d'ecoute
			System.out.println("Connecte !");
			// recuperation de la donnee client
			/*
			this.out=new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			while((inputLine=in.readLine()) != null) {
				out.println(inputLine);
			}
			*/
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			this.nom_client = in.readUTF();
			// traitement
			this.message_bienvenue = "Bienvenue " + nom_client + ". Tu est desormais connecte.";
			// envoi de la donnee au client
			this.out = new DataOutputStream(clientSocket.getOutputStream());
			this.out.writeUTF(message_bienvenue);
			this.ready=true;
			
		}catch(Exception e){
			System.err.println("Impossible d'ecouter le port "+port_client+" ou de s'y connecter.");
			System.err.println(e.getMessage());
		}
	}
	
	public boolean getReady() {
		return this.ready;
	}
	
	public String getClientName() {
		return this.nom_client;
	}
	
	

}
