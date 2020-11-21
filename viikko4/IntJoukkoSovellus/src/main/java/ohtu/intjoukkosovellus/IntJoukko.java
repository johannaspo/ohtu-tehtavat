
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        alusta(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        alusta(kapasiteetti, OLETUSKASVATUS);

    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetin on oltava yli 0");
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoon on oltava yli 0");
        }
        alusta(kapasiteetti, kasvatuskoko);

    }
    
    public void alusta(int kapasiteetti, int kasvatuskoko) {
        ljono = new int[kapasiteetti];
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (alkioidenLkm == 0) {
            ljono[0] = luku;
            alkioidenLkm++;
            return true;
        } 
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm < ljono.length) {
                int[] vanhaTaulukko = ljono;
                ljono = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(vanhaTaulukko, ljono);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int kohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                kohta = i;
                break;
            }
        }
        if (kohta != -1) {
            for (int j = kohta; j < alkioidenLkm - 1; j++) {
                ljono[j] = ljono[j + 1];
            }
            alkioidenLkm--;
            return true;
        }


        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm; i++) {
            tuotos += ljono[i];
            if(i < alkioidenLkm - 1) {
                tuotos += ", ";
            }
        }
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
    
    public int haeLuku(int kohta) {
        return ljono[kohta];
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko(a.mahtavuus() + b.mahtavuus());
        for (int i = 0; i < a.mahtavuus(); i++) {
            yhdiste.lisaa(a.haeLuku(i));
        }
        for (int i = 0; i < b.mahtavuus(); i++) {
            yhdiste.lisaa(b.haeLuku(i));
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        for (int i = 0; i < a.mahtavuus(); i++) {
            if (b.kuuluu(a.haeLuku(i))) {
                leikkaus.lisaa(a.haeLuku(i));
            }
        }
        return leikkaus;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        for (int i = 0; i < a.mahtavuus(); i++) {
            if (!b.kuuluu(a.haeLuku(i))) {
                erotus.lisaa(a.haeLuku(i));
            }
        }
 
        return erotus;
    }
        
}
