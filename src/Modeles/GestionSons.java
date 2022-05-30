package Modeles;

public class GestionSons {
	
	private SoundPlayer musique, effetsSonores;
	
	public GestionSons(SoundPlayer musique, SoundPlayer effetsSonores) {
		this.musique=musique;
		this.effetsSonores=effetsSonores;
	}
	
	public void playSon(int n) {
		this.effetsSonores.setNumSon(n);
		this.effetsSonores.jouerSon();
	}
	
	public void setVolumeSon(int v) {
		this.effetsSonores.setVolume2(v);
	}
	
	public void stopSon() {
		this.effetsSonores.stopSound();
	}
	
	public void changeMusique(int n) {
		this.musique.setNumSon(n);
	}
	
	public void playMusique() {
		this.musique.loopSon();
	}
	
	public void stopMusique() {
		this.musique.stopSound();
	}
	public void setVolumeMusique(int v) {
		this.musique.setVolume(v);
	}
	
}
