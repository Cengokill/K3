package Modeles;

public class Position {
    public int x;
    public int y;

    public Position(int i, int j) {
        x = i;
        y = j;
    }

    public String toString() {
        String res = "";
        res += x+","+y;
        return res;
    }
}
