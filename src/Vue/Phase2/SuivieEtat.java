package Vue.Phase2;

import java.util.ArrayList;
import Modeles.Position;

/**
 *
 * @author farid
 */
public class SuivieEtat {
    public EtatVue etat;
    public int etage;
    public int colonne;
    public int typejoueur;
    public ArrayList < Position > baseselection;
    public int selectedpieceetage;
    public int selectedpiececolonne;
    
    public SuivieEtat () {
        etat = EtatVue.ALLREFRESH;
        baseselection = new ArrayList();
    }
    
    public void setetage(int i) {
        this.etage = i;
    }
    
    public void setcolonne(int j) {
        this.colonne = j;
    }
    
     public void setjoueur(int j) {
        this.typejoueur = j;
    }
}
