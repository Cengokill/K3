package Modeles;

public class Position {
    public int etage;
    public int rang;

    public Position(int etage, int rang) {
    	this.etage = etage;
    	this.rang = rang;
    }

    public String toString() {
        String res = "";
        res += "("+this.etage + "," + this.rang+")";
        return res;
    }
}
