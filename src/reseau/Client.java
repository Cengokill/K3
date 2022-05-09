package reseau;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	String ip_serveur;
	int port_serveur;
	DataInputStream in;
	Socket client;
	String s1;
	String nom_client;

	public void main(String arcs[]) throws UnknownHostException, IOException {
		this.nom_client = "Killian";
		this.ip_serveur = "127.0.0.1";
		this.port_serveur = 1997;
		this.client = new Socket(ip_serveur, port_serveur);
		// envoi donnee au serveur
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		out.writeUTF(nom_client);
		// recuperation donnee serveur
		this.in = new DataInputStream(client.getInputStream());
		this.s1 = in.readUTF();
		System.out.println(s1);
	}
}
