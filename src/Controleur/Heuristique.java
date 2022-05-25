package Controleur;

public class Heuristique {
    int prem;
    int deux;
    int trois;

    public void setprem(int prem) {
        this.prem = prem;
    }

    public int getprem() {
        return prem;
    }

    public void setdeux(int deux) {
        this.deux = deux;
    }

    public int getdeux() {
        return deux;
    }

    public void settrois(int trois) {
        this.trois = trois;
    }

    public int gettrois() {
        return prem;
    }

    public String toString() {
        String res = "";
        res += "Premier Heuristique Difference : " + prem;
        res += "\nDeuxieme Heuristique Nb pieces : " + deux;
        res += "\nTroisieme Heuristique nb coups possibles adv : " + deux + "\n";
        return res;
    }
}